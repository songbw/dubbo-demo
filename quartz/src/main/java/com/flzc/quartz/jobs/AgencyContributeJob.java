package com.flzc.quartz.jobs;

import com.flzc.quartz.entity.Agency;
import com.flzc.quartz.entity.AgencyContribution;
import com.flzc.quartz.service.AgencyContributeService;
import com.flzc.quartz.util.DateUtil;
import org.hibernate.internal.SessionFactoryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 *  统计经纪人业绩，报备数，report_num，deal_num，commission
 */
public class AgencyContributeJob implements FlzcJob {

    private static  final Logger LOGGER = LoggerFactory.getLogger(AgencyContributeJob.class);
    private static  final String JOB_CODE = "agencyContributeJob";
    private static final Object lock = new Object();

    @Autowired
    private AgencyContributeService contributeService;


    @Override
    public void execute() {

        synchronized (lock){
            LOGGER.info( JOB_CODE +  "================经纪人周，月业绩汇总定时任务开始================" );
            long start = System.currentTimeMillis();
            try {
                List<Agency> agencies = this.contributeService.queryAllAgency();
                this.contributeService.saveStatisticsWeek(agencies);
                this.contributeService.saveStatisticsMonth(agencies);
                this.contributeService.saveStatisticsAll(agencies);
            } catch (Exception e) {
                LOGGER.error("经纪人周，月业绩汇总定时任务异常",e);
            }
            LOGGER.info( JOB_CODE +  "================经纪人周，月业绩汇总定时任务结束================times=" + DateUtil.calIntervalSec(start,System.currentTimeMillis()));
        }
    }
}
