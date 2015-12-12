package com.flzc.quartz.jobs;

import com.flzc.quartz.service.AuctionActivityService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 这个类在项目启动时，初始化定时任务时，加载到spring
 * 1.定时将已结束的竞拍活动胜出者选出.
 * 2.将未拍的者退款
 */
public class AuctionActivityWinnerJob implements FlzcJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuctionActivityWinnerJob.class);

    private static final Object lock = new Object();

    @Autowired
    private AuctionActivityService auctionActivityService;


    @Override
    public void execute() {
        synchronized (lock) {
            LOGGER.info("==============竞拍定时任务[Winner]starting================");
            long start = System.currentTimeMillis();
            try {
                List<Map<String, Object>> activities = auctionActivityService.queryEndRecordToday();
                if(activities.isEmpty()){
                    LOGGER.info("==============暂无结束的竞拍活动要处理==================");
                    return ;
                }
                for (Map<String, Object> act : activities) {
                    String actId = act.get("id").toString();
                    try {
                        //设置拍的者
                        Integer activityId = Integer.valueOf(actId);
                        //活动是否达到竞拍目标价
                        int targetPrice = auctionActivityService.queryInvalid(activityId);
                        if(targetPrice >  0){
                            auctionActivityService.updateForWinner(activityId,targetPrice);
                        }else {
                            //设置已处理
                            auctionActivityService.updateHandleStatus(activityId);
                            LOGGER.info("==============竞拍活动流拍================ID=" + activityId);
                        }

                        //查询要退款的数据
                        List<Map<String, Object>> orders = auctionActivityService.queryReturnList(activityId);
                        for (Map order : orders) {
                            String orderSeq = order.get("orderSeq").toString();
                            String userId = order.get("userId").toString();
                            //调用退款接口
                            auctionActivityService.queryMoneyBack(Integer.valueOf(userId), orderSeq);
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.error("竞拍活动ID号不是数字：ID=" + actId, e);
                    } catch (Exception e) {
                        LOGGER.error("设置竞拍活动ID=" + actId + "异常", e);
                        //todo 加日志
                    }
                }
            } catch (Exception e) {
                LOGGER.error("竞拍订时任务异常", e);
            }
            LOGGER.info("==============竞拍定时任务[Winner]ending================times:" + DateUtil.calIntervalSec(start, System.currentTimeMillis()));

        }
    }
}
