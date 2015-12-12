package com.flzc.service.service;

import com.flzc.scene.filing.api.entity.SceneStatistics;
import com.flzc.service.bean.SceneStatisticsBean;
import com.flzc.service.exception.SceneException;
import com.sun.jersey.core.spi.scanning.ScannerException;

import java.util.List;

/**
 *  数据统计业务实现类
 * Created by song on 2015/12/2.
 */
public interface SceneStatisticsBizService {

    /**
     * 查询周数据
     * @param tokenId
     * @param fromDate
     * @param toDate
     * @return
     * @throws ScannerException
     */
    public SceneStatisticsBean queryWeek(String tokenId, long fromDate, long toDate) throws SceneException;

    /**
     * 查询月数据（1，5，10，15，20，25，30，31）
     * @param tokenId
     * @param monthDate
     * @return
     * @throws ScannerException
     */
    public SceneStatisticsBean queryMonth(String tokenId, long monthDate) throws SceneException;

    /**
     * 查询年数据（1，2，3，4，5，6，7，8，9，10，11，12）
     * @param tokenId
     * @param yearDate
     * @return
     * @throws SceneException
     */
    public SceneStatisticsBean queryYear(String tokenId,long yearDate) throws SceneException;

    /**
     * 周总数
     * @param tokenId
     * @param fromDate
     * @param toDate
     * @return
     * @throws SceneException
     */
    public SceneStatistics queryWeekTotal(String tokenId, long fromDate, long toDate) throws SceneException;

    /**
     * 月总是
     * @param tokenId
     * @param monthDate
     * @return
     * @throws SceneException
     */
    public SceneStatistics queryMonthTotal(String tokenId, long monthDate) throws  SceneException;

    /**
     * 年总数
     * @param tokenId
     * @param yearDate
     * @return
     * @throws SceneException
     */
    public SceneStatistics queryYearTotal(String tokenId, long yearDate) throws SceneException;
}
