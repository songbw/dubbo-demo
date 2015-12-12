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
public class AgencyOfficeStatisticsJob implements FlzcJob {

    private static  final Logger LOGGER = LoggerFactory.getLogger(AgencyOfficeStatisticsJob.class);
    private static  final  Object lock = new Object();
    @Autowired
    private AgencyOfficeStatisticsService officeStatisticsService;

    @Override
    public void execute() {
              synchronized (lock){
                  LOGGER.info("===================门店，日，周，月，全部统计定时任务starting===================");
                  long start;
                  try {
                      List<Map<String, Object>> offices = this.officeStatisticsService.queryAllOffice();

                      // 周统计
                      try {
                          start = System.currentTimeMillis();
                          LOGGER.info("===================周统计定时任务starting===================");
                          this.officeStatisticsService.updateAllWeekStatsInvalid();
                          Map<String, Object> weekReport = this.officeStatisticsService.queryReportWeekStat();
                          Map<String, Object> weekVisit = this.officeStatisticsService.queryVisitWeekStat();
                          Map<String, Object> weekDeal = this.officeStatisticsService.queryDealWeekStat();
                          Map<String, Object> weekComm = this.officeStatisticsService.queryCommissionWeekStat();
                          for (Map<String, Object> office : offices ){
                              AgencyOfficeWeekStatistics week = new AgencyOfficeWeekStatistics();
                              String officeCode = office.get("officeCode").toString();
                              week.setOfficeCode(Integer.valueOf(officeCode) );
                              Object report = weekReport.get(officeCode);
                              report = (report==null?0:report);
                              week.setReportNum(Integer.valueOf(report.toString()));

                              Object visit = weekVisit.get(officeCode);
                              visit = (visit==null?0:visit);
                              week.setVisitNum(Integer.valueOf(visit.toString()));

                              Object deal = weekDeal.get(officeCode);
                              deal = (deal==null?0:deal);
                              week.setDealNum(Integer.valueOf(deal.toString()));

                              Object comm = weekComm.get(officeCode);
                              comm = (comm==null?0:comm);
                              week.setCommission(Float.valueOf(comm.toString()));
                              this.officeStatisticsService.save(week);
                          }
                          LOGGER.info("===================周统计定时任务ending===================times=" + DateUtil.calIntervalSec(start,System.currentTimeMillis()));
                      } catch (Exception e) {
                          LOGGER.error("周统计定时任务异常",e);
                      }

                      //月
                      try {
                          start  = System.currentTimeMillis();
                          LOGGER.info("===================月统计定时任务starting===================");
                          this.officeStatisticsService.updateAllMonthStatsInvalid();
                          Map<String, Object> monthReport = this.officeStatisticsService.queryReportMonthStat();
                          Map<String, Object> monthVisit = this.officeStatisticsService.queryVisitMonthStat();
                          Map<String, Object> monthDeal = this.officeStatisticsService.queryDealMonthStat();
                          Map<String, Object> monthComm = this.officeStatisticsService.queryCommissionMonthStat();
                          for (Map<String, Object> office : offices ){
                              AgencyOfficeMonthStatistics month = new AgencyOfficeMonthStatistics();
                              String officeCode = office.get("officeCode").toString();
                              month.setOfficeCode(Integer.valueOf(officeCode) );
                              Object report = monthReport.get(officeCode);
                              report = (report==null?0:report);
                              month.setReportNum(Integer.valueOf(report.toString()));

                              Object visit = monthVisit.get(officeCode);
                              visit = (visit==null?0:visit);
                              month.setVisitNum(Integer.valueOf(visit.toString()));

                              Object deal = monthDeal.get(officeCode);
                              deal = (deal==null?0:deal);
                              month.setDealNum(Integer.valueOf(deal.toString()));

                              Object comm = monthComm.get(officeCode);
                              comm = (comm==null?0:comm);
                              month.setCommission(Float.valueOf(comm.toString()));
                              this.officeStatisticsService.save(month);
                          }
                          LOGGER.info("===================月统计定时任务ending===================times=" + DateUtil.calIntervalSec(start,System.currentTimeMillis()));
                      } catch (Exception e) {
                          LOGGER.error("月统计定时任务异常",e);
                      }


                      //全部
                      try {
                          start  = System.currentTimeMillis();
                          LOGGER.info("===================历史统计定时任务starting===================");
                          this.officeStatisticsService.updateAllMonthStatsInvalid();
                          Map<String, Object> historyReport = this.officeStatisticsService.queryReportHistoryStat();
                          Map<String, Object> historyVisit = this.officeStatisticsService.queryVisitHistoryStat();
                          Map<String, Object> historyDeal = this.officeStatisticsService.queryDealHistoryStat();
                          Map<String, Object> historyComm = this.officeStatisticsService.queryCommissionHistoryStat();
                          for (Map<String, Object> office : offices ){
                              AgencyOfficeHistoryStatistics history= new AgencyOfficeHistoryStatistics();
                              String officeCode = office.get("officeCode").toString();
                              history.setOfficeCode(Integer.valueOf(officeCode) );
                              Object report = historyReport.get(officeCode);
                              report = (report==null?0:report);
                              history.setReportNum(Integer.valueOf(report.toString()));

                              Object visit = historyVisit.get(officeCode);
                              visit = (visit==null?0:visit);
                              history.setVisitNum(Integer.valueOf(visit.toString()));

                              Object deal = historyDeal.get(officeCode);
                              deal = (deal==null?0:deal);
                              history.setDealNum(Integer.valueOf(deal.toString()));

                              Object comm = historyComm.get(officeCode);
                              comm = (comm==null?0:comm);
                              history.setCommission(Float.valueOf(comm.toString()));
                              this.officeStatisticsService.save(history);
                          }
                          LOGGER.info("===================历史统计定时任务ending===================times=" + DateUtil.calIntervalSec(start,System.currentTimeMillis()));
                      } catch (Exception e) {
                          LOGGER.error("历史统计定时任务异常",e);
                      }

                  }catch (Exception e){
                         LOGGER.error("门店业绩统计定时任务异常",e);
                  }

              }
    }
}
