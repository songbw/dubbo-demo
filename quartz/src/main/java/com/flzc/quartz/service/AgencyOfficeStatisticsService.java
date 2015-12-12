package com.flzc.quartz.service;

import com.flzc.quartz.entity.AgencyOfficeDayStatistics;
import com.flzc.quartz.entity.AgencyOfficeHistoryStatistics;
import com.flzc.quartz.entity.AgencyOfficeMonthStatistics;
import com.flzc.quartz.entity.AgencyOfficeWeekStatistics;

import java.util.List;
import java.util.Map;

/**
 * 门店业绩统计，日，周，月，统计
 */
public interface AgencyOfficeStatisticsService {

    List<Map<String, Object>> queryAllOffice();

    public void save(AgencyOfficeDayStatistics statistics) throws Exception;

    void save(AgencyOfficeWeekStatistics statistics) throws Exception;

    void save(AgencyOfficeMonthStatistics statistics) throws Exception;

    void save(AgencyOfficeHistoryStatistics statistics) throws Exception;

    public void updateAllDayStatsInvalid();

    void updateAllWeekStatsInvalid();

    void updateAllMonthStatsInvalid();

    void updateAllHistoryStatsInvalid();

    public  Map<String, Object> queryReportDayStat();
    public Map<String, Object> queryVisitDayStat();
    public Map<String, Object> queryDealDayStat();
    public Map<String, Object> queryCommissionDayStat();





    public Map<String, Object> queryReportWeekStat();

    Map<String,Object> queryVisitWeekStat();

    Map<String,Object> queryDealWeekStat();

    Map<String,Object> queryCommissionWeekStat();

    public Map<String, Object> queryReportMonthStat();

    Map<String,Object> queryVisitMonthStat();

    Map<String,Object> queryVisitHistoryStat();

    Map<String,Object> queryDealMonthStat();

    Map<String,Object> queryDealHistoryStat();

    Map<String,Object> queryCommissionMonthStat();


    Map<String, Object> queryReportHistoryStat();

    Map<String,Object> queryCommissionHistoryStat();

    //

}
