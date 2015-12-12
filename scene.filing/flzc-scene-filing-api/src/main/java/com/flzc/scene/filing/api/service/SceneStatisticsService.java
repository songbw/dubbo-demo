package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneStatistics;

import java.util.List;
import java.util.Map;

/**
 *   数据统计服务
 * Created by song on 2015/12/2.
 */
public interface SceneStatisticsService {

    /**
     *  查询周数据
     * @param sceneId
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<SceneStatistics> queryWeek(Integer sceneId, long fromDate, long toDate) ;

    /**
     * 查询月数据（1，5，10，15，20，25，30，31）
     * @param sceneId
     * @param monthDate
     * @return
     */
    public List<SceneStatistics> queryMonth(Integer sceneId, long monthDate) ;

    /**
     *  查询年数据（1，2，3，4，5，6，7，8，9，10，11，12）
     * @param sceneId
     * @param yearDate
     * @return
     */
    public List<SceneStatistics> queryYear(Integer sceneId,long yearDate) ;

    /**
     *  周总数
     * @param sceneId
     * @param fromDate
     * @param toDate
     * @return
     */
    public SceneStatistics queryWeekTotal(Integer sceneId, long fromDate, long toDate) ;

    /**
     *  月总是
     * @param sceneId
     * @param monthDate
     * @return
     */
    public SceneStatistics queryMonthTotal(Integer sceneId, long monthDate) ;

    /**
     *  年总数
     * @param sceneId
     * @param yearDate
     * @return
     */
    public SceneStatistics queryYearTotal(Integer sceneId, long yearDate) ;
}
