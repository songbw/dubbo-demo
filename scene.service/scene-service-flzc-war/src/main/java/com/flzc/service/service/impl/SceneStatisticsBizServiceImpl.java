package com.flzc.service.service.impl;

import com.flzc.base.util.Memcached;
import com.flzc.scene.filing.api.entity.SceneStatistics;
import com.flzc.scene.filing.api.service.SceneStatisticsService;
import com.flzc.service.bean.SceneStatisticsBean;
import com.flzc.service.bean.SceneStatisticsView;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.exception.SceneResponseException;
import com.flzc.service.service.SceneStatisticsBizService;
import com.sun.jersey.core.spi.scanning.ScannerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据统计业务实现类
 * Created by song on 2015/12/2.
 */
@Service
public class SceneStatisticsBizServiceImpl implements SceneStatisticsBizService {
    private static  final Logger logger = LoggerFactory.getLogger(SceneStatisticsBizServiceImpl.class);

    @Autowired
    private SceneStatisticsService sceneStatisticsService ;
    /**
     * 查询周数据
     *
     * @param tokenId
     * @param fromDate
     * @param toDate
     * @return
     * @throws ScannerException
     */
    @Override
    public SceneStatisticsBean queryWeek(String tokenId, long fromDate, long toDate) throws SceneException {
        int sceneId = (int) Memcached.get(tokenId);
        SceneStatisticsBean sceneStatisticsBean = new SceneStatisticsBean() ;
        SceneStatistics sceneStatistics = new SceneStatistics() ;
        List<SceneStatistics> sceneStatisticses = sceneStatisticsService.queryWeek(sceneId,fromDate,toDate) ;
        sceneStatisticsBean.setSceneStatisticses(sceneStatisticses);
        sceneStatistics = queryWeekTotal(tokenId,fromDate,toDate) ;
        sceneStatisticsBean.setFilingTotal(sceneStatistics.getFilingStatistics());
        sceneStatisticsBean.setDealTotal(sceneStatistics.getDealStatistics());
        sceneStatisticsBean.setVisitTotal(sceneStatistics.getVisitStatistics());
        return sceneStatisticsBean;
    }

    /**
     * 查询月数据（1，5，10，15，20，25，30，31）
     *
     * @param tokenId
     * @param monthDate
     * @return
     * @throws ScannerException
     */
    @Override
    public SceneStatisticsBean queryMonth(String tokenId, long monthDate) throws SceneException {
        int sceneId = (int) Memcached.get(tokenId);
        SceneStatisticsBean sceneStatisticsBean = new SceneStatisticsBean() ;
        List<SceneStatistics> sceneStatisticses =  sceneStatisticsService.queryMonth(sceneId,monthDate) ;
        SceneStatistics sceneStatistics = sceneStatisticsService.queryMonthTotal(sceneId,monthDate) ;
        sceneStatisticsBean.setSceneStatisticses(sceneStatisticses);
        sceneStatisticsBean.setDealTotal(sceneStatistics.getDealStatistics());
        sceneStatisticsBean.setFilingTotal(sceneStatistics.getFilingStatistics());
        sceneStatisticsBean.setVisitTotal(sceneStatistics.getVisitStatistics());
        return sceneStatisticsBean;
    }

    /**
     * 查询年数据（1，2，3，4，5，6，7，8，9，10，11，12）
     *
     * @param tokenId
     * @param yearDate
     * @return
     * @throws SceneException
     */
    @Override
    public SceneStatisticsBean queryYear(String tokenId, long yearDate) throws SceneException {
        int sceneId = (int) Memcached.get(tokenId);
        SceneStatisticsBean sceneStatisticsBean = new SceneStatisticsBean() ;
        List<SceneStatistics> sceneStatisticses = sceneStatisticsService.queryYear(sceneId,yearDate) ;
        SceneStatistics sceneStatistics = sceneStatisticsService.queryYearTotal(sceneId,yearDate) ;
        sceneStatisticsBean.setSceneStatisticses(sceneStatisticses);
        sceneStatisticsBean.setDealTotal(sceneStatistics.getDealStatistics());
        sceneStatisticsBean.setFilingTotal(sceneStatistics.getFilingStatistics());
        sceneStatisticsBean.setVisitTotal(sceneStatistics.getVisitStatistics());
        return sceneStatisticsBean;
    }

    /**
     * 周总数
     *
     * @param tokenId
     * @param fromDate
     * @param toDate
     * @return
     * @throws SceneException
     */
    @Override
    public SceneStatistics queryWeekTotal(String tokenId, long fromDate, long toDate) throws SceneException {
        int sceneId = (int) Memcached.get(tokenId);
        SceneStatistics sceneStatistics = new SceneStatistics() ;
        sceneStatistics = sceneStatisticsService.queryWeekTotal(sceneId,fromDate,toDate) ;
        ErrorBean errorBean = new ErrorBean() ;

        return sceneStatistics;
    }

    /**
     * 月总是
     *
     * @param tokenId
     * @param monthDate
     * @return
     * @throws SceneException
     */
    @Override
    public SceneStatistics queryMonthTotal(String tokenId, long monthDate) throws SceneException {
        int sceneId = (int) Memcached.get(tokenId);
        SceneStatisticsView sceneStatisticsView = new SceneStatisticsView() ;
        SceneStatistics sceneStatistics = sceneStatisticsService.queryMonthTotal(sceneId,monthDate) ;
        return sceneStatistics;
    }

    /**
     * 年总数
     *
     * @param tokenId
     * @param yearDate
     * @return
     * @throws SceneException
     */
    @Override
    public SceneStatistics queryYearTotal(String tokenId, long yearDate) throws SceneException {
        int sceneId = (int) Memcached.get(tokenId);
        SceneStatistics sceneStatistics = new SceneStatistics() ;
        sceneStatistics = sceneStatisticsService.queryYearTotal(sceneId,yearDate) ;
        ErrorBean errorBean = new ErrorBean() ;
        return sceneStatistics;
    }
}
