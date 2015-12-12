package com.flzc.quartz.jobs;

import com.flzc.quartz.service.AgencyTopsMonthService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 生成经纪人排行榜数据:近30天
 */
@Deprecated
public class AgencyTopsMonthJob implements FlzcJob {
    private static  final Logger LOGGER = LoggerFactory.getLogger(AgencyTopsMonthJob.class );
    private static final Object lock = new Object();

    @Autowired
    private AgencyTopsMonthService topsMonthService;

    @Override
    public void execute() {
           synchronized (lock){
               long start = System.currentTimeMillis();
               LOGGER.info("=================生成经纪人最近30天排行榜数据定时任务starting============");
               try {
                   this.topsMonthService.updateAllInvalid();
                   List<Map<String, Object>> report = this.topsMonthService.queryReport();
                   List<Map<String, Object>> visit = this.topsMonthService.queryVisit();
                   List<Map<String, Object>> deal = this.topsMonthService.queryDeal();
                   List<Map<String, Object>> comm = this.topsMonthService.queryCommission();
               } catch (Exception e) {
                   LOGGER.error("经纪人排行榜定时任务异常",e);
               }
               LOGGER.info("=================生成经纪人最近30天排行榜数据定时任务ending============time=" + DateUtil.calIntervalSec(start,System.currentTimeMillis()));
           }
    }
}
