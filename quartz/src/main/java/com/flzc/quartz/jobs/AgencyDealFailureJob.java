package com.flzc.quartz.jobs;

import com.flzc.quartz.service.AgencyClientIntentService;
import com.flzc.quartz.util.CommonUtils;
import com.flzc.quartz.util.DateUtil;
import com.flzc.quartz.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 未成交失败定时任务 ，带看成功后，30天内没有成交算是认购失败，也就是未成交
 */
public class AgencyDealFailureJob implements FlzcJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgencyDealFailureJob.class );
    private final static  Object lock = new Object();

    @Autowired
    AgencyClientIntentService intentService;


    @Value("${message.api.url}")
    private String messageUrl;

    @PostConstruct
    public void init(){
        System.out.println(messageUrl);
    }

    @Override
    public void execute() {
        synchronized (lock){
            long start = System.currentTimeMillis();
           LOGGER.info("======================经纪人未成交定时任务starting=================");
            int total = 0;
            int pageSize = 50;
            int failCounter= 0;
            List<String> messageUserId = Collections.EMPTY_LIST;
            try {
                total = this.intentService.queryTimeoutIntentTotal();
                if(total==0) return;
                int totalPage = CommonUtils.getTotalPage(total, pageSize);

                messageUserId = new ArrayList<>();
                for (int i = 1 ; i<= totalPage ; i++){
                    List<Map<String, Object>> rows = this.intentService.queryTimeoutIntent(i, pageSize);
                    for (Map<String,Object> row : rows){
                        String id = row.get("id").toString();
                        String agencyId = row.get("agencyId").toString();
                        String clientId = row.get("clientId").toString();
                        try {
                            this.intentService.updateDealFailure(Integer.valueOf(id));
                            String code = this.intentService.queryAgency(Integer.valueOf(agencyId));
                            if(StringUtils.isNotBlank(code)){
                                messageUserId.add(code);
                            }

                        } catch (Exception e) {
                            LOGGER.error("设置购房意向异常intentId=" + id ,e);
                            failCounter ++;
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error("购房意向未成交定时任务异常",e);
            }
            for (int i = 0; i< messageUserId.size();i++){
                String userId =  messageUserId.get(i);
                String url = String.format("%s?userType=%s&userId=%s&msgCode=%s", messageUrl, 2, userId, "120");//120表示消息模板编码，2表示用户类型是经纪人
                try {
                    HttpUtils.get(url);
                } catch (Exception e) {
                    LOGGER.error("发送消息失败：url-->" + url);
                }
            }
            LOGGER.info(String.format("======================经纪人未成交定时任务ending=================总共处理:%1s,失败:%2s,耗时:%3s秒",total,failCounter
                    , DateUtil.calIntervalSec(start,System.currentTimeMillis())) );
        }
    }

}
