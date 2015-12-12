package com.flzc.quartz.service;

import com.flzc.quartz.entity.AgencyReportCount;
import com.flzc.quartz.entity.SceneStatistics;

import java.util.List;
import java.util.Map;

/**
 *  案场数据统计服务  日，周，月
 * Created by song on 2015/12/1.
 */
public interface SceneStatisticsService {

    /**
     *  报备统计 天
     * @return
     */
    public List<Map<String,Object>> queryFilingByDay();

    /**
     *  报备统计 月
     * @return
     */
    public List<Map<String,Object>> queryFilingByMonth();

    /**
     *   保存数据统计表
     * @param sceneStatistics
     * @throws Exception
     */
    void saveOrUpdateFilingStatistics(SceneStatistics sceneStatistics) throws Exception;

    /**
     *  更加创建时间查询指定数据
     * @return
     */
    public Map<String,Object> findDayByCreateAt(Integer sceneId) ;

    public Map<String,Object> findMonthByCreateAt(Integer sceneId) ;

    /**
     *  看房统计
     * @return
     */
    public List<Map<String,Object>> queryVisitByDay();

    public List<Map<String,Object>> queryVisitByMonth();

    /**
     *  核销统计
     * @return
     */
    public List<Map<String,Object>> queryDealByDay();

    public List<Map<String,Object>> queryDealByMonth();

}
