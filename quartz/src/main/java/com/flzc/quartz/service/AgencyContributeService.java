package com.flzc.quartz.service;

import com.flzc.quartz.entity.*;

import java.util.List;
import java.util.Map;

/**
 *  经纪人业绩接口
 */
public interface AgencyContributeService {

    public void saveOrUpdate(AgencyContribution contribution) throws Exception;


   //  public List<AgencyContribution> queryAll();

    void deleteAll() throws Exception;

    void deleteDayAll() throws Exception;

    void saveOrUpdateDay(AgencyDayContribution contribution) throws Exception;

    void deleteWeekAll() throws Exception;

    void saveOrUpdateWeek(AgencyWeekContribution contribution) throws Exception;

    void saveOrUpdateMonth(AgencyMonthContribution contribution) throws Exception;

    void deleteMonthAll() throws Exception;

    /**
     * 查询出所有的
     * @return
     */
    public List<Agency> queryAllAgency() throws Exception;


    Map<String, Object> queryReportWeek();

    Map<String, Object> queryVisitWeek();

    Map<String, Object> queryDealWeek();

    Map<String, Object> queryCommissionWeek();

    Map<String, Object> queryReportMonth();

    Map<String, Object> queryVisitMonth();

    Map<String, Object> queryDealMonth();

    Map<String, Object> queryCommissionMonth();

    Map<String, Object> queryReportDay();

    Map<String, Object> queryReportAll();

    Map<String, Object> queryVisitDay();

    Map<String, Object> queryVisitAll();

    Map<String, Object> queryDealDay();

    Map<String, Object> queryDealAll();

    Map<String, Object> queryCommissionDay();

    Map<String, Object> queryCommissionAll();

    void saveStatisticsDay(List<Agency> agencies) throws Exception;

    void saveStatisticsWeek(List<Agency> agencies) throws Exception;

    void saveStatisticsMonth(List<Agency> agencies) throws Exception;

    void saveStatisticsAll(List<Agency> agencies) throws Exception;
}
