package com.flzc.quartz.service;

import com.flzc.quartz.entity.AgencyReportCount;
import com.flzc.quartz.entity.AgencySeeCount;

import java.util.List;
import java.util.Map;

/**
 *  经纪人业绩统计，日，周，月，年
 */
public interface AgencyStatisticsService {

    /**
     *  按天统计
     * @return
     */
    public List<Map<String,Object>> queryReportByDay();

    public List<Map<String,Object>> queryReportByWeek();

    public List<Map<String,Object>> queryReportByMonth();

    void saveOrUpdateStatistics(AgencyReportCount stat) throws Exception;

    /**
     * 带看
     * @return
     */
    public List<Map<String,Object>> querySeeByDay();

    public List<Map<String,Object>> querySeeByWeek();

    public List<Map<String,Object>> querySeeByMonth();

    void saveOrUpdateStatistics(AgencySeeCount stat) throws Exception;

    List<Map<String, Object>> querySeeTotal();
}
