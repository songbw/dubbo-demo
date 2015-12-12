package com.flzc.quartz.jobs;

import com.flzc.quartz.entity.AgencyOfficeDayStatistics;
import com.flzc.quartz.entity.AgencyOfficeHistoryStatistics;
import com.flzc.quartz.entity.AgencyOfficeMonthStatistics;
import com.flzc.quartz.entity.AgencyOfficeWeekStatistics;
import com.flzc.quartz.service.AgencyOfficeStatisticsService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 统计门店报备，带看，成交业绩。  按日，周，月，
 */
public class AgencyOfficeDayStatisticsJob implements FlzcJob {

    private static  final Logger LOGGER = LoggerFactory.getLogger(AgencyOfficeDayStatisticsJob.class);
    private static  final  Object lock = new Object();
    @Autowired
    private AgencyOfficeStatisticsService officeStatisticsService;

    @Override
    public void execute() {
              synchronized (lock){
                  long start = System.currentTimeMillis();
                  try {
                      List<Map<String, Object>> offices = this.officeStatisticsService.queryAllOffice();
                      LOGGER.info("===================日统计定时任务starting===================");
                      this.officeStatisticsService.updateAllDayStatsInvalid();
                      Map<String, Object> dayReport = this.officeStatisticsService.queryReportDayStat();
                      Map<String, Object> dayVisit = this.officeStatisticsService.queryVisitDayStat();
                      Map<String, Object> dayDeal = this.officeStatisticsService.queryDealDayStat();
                      Map<String, Object> dayComm = this.officeStatisticsService.queryCommissionDayStat();
                      for (Map<String, Object> office : offices ){
                          AgencyOfficeDayStatistics day = new AgencyOfficeDayStatistics();
                          String officeCode = office.get("officeCode").toString();
                          day.setOfficeCode(Integer.valueOf(officeCode) );
                          Object report = dayReport.get(officeCode);
                          report = (report==null?0:report);
                          day.setReportNum(Integer.valueOf(report.toString()));

                          Object visit = dayVisit.get(officeCode);
                          visit = (visit==null?0:visit);
                          day.setVisitNum(Integer.valueOf(visit.toString()));

                          Object deal = dayDeal.get(officeCode);
                          deal = (deal==null?0:deal);
                          day.setDealNum(Integer.valueOf(deal.toString()));

                          Object comm = dayComm.get(officeCode);
                          comm = (comm==null?0:comm);
                          day.setCommission(Float.valueOf(comm.toString()));
                          this.officeStatisticsService.save(day);
                      }
                      LOGGER.info("===================日统计定时任务ending===================times=" + DateUtil.calIntervalSec(start,System.currentTimeMillis()));
                  } catch (Exception e) {
                      LOGGER.error("日统计定时任务异常",e);
                  }


              }
    }
}
