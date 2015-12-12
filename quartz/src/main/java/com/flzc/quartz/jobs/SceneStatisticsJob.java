package com.flzc.quartz.jobs;

import com.flzc.quartz.entity.Agency;
import com.flzc.quartz.entity.AgencyReportCount;
import com.flzc.quartz.entity.AgencySeeCount;
import com.flzc.quartz.entity.SceneStatistics;
import com.flzc.quartz.service.SceneStatisticsService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  案场统计信息
 * Created by song on 2015/12/1.
 */
public class SceneStatisticsJob implements FlzcJob {
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneStatisticsJob.class);
    private static final Object lock = new Object();

    @Autowired
    private SceneStatisticsService sceneStatisticsService ;

    @Override
    public void execute() {
        synchronized (lock){
            LOGGER.info("===================案场数据统计分析任务start===================");
            long start = System.currentTimeMillis();
            SceneStatistics sceneStatistics =null ;
            try {
                //报备 DAY
                List<Map<String, Object>> dayFilingStatistics = sceneStatisticsService.queryFilingByDay();
                for (Map<String, Object> m : dayFilingStatistics) {
                    int sceneId = Integer.valueOf(m.get("sceneId").toString()) ;
                    Map<String, Object> mapId = sceneStatisticsService.findDayByCreateAt(sceneId) ;
                    sceneStatistics = getSceneStatistics(mapId) ;
                    int filingStatistics = Integer.valueOf(m.get("filingStatistics").toString()) ;
                    sceneStatistics.setSceneId(sceneId);
                    sceneStatistics.setFilingStatistics(filingStatistics);
                    sceneStatistics.setType(1);
                    this.sceneStatisticsService.saveOrUpdateFilingStatistics(sceneStatistics);
                }

                //报备 MONTH
                List<Map<String, Object>> monthFilingStatistics = sceneStatisticsService.queryFilingByMonth();
                for (Map<String, Object> m : monthFilingStatistics) {
                    int sceneId = Integer.valueOf(m.get("sceneId").toString()) ;
                    Map<String, Object> mapId = sceneStatisticsService.findMonthByCreateAt(sceneId) ;
                    sceneStatistics = getSceneStatistics(mapId) ;
                    int filingStatistics = Integer.valueOf(m.get("filingStatistics").toString()) ;
                    sceneStatistics.setSceneId(sceneId);
                    sceneStatistics.setFilingStatistics(filingStatistics);
                    sceneStatistics.setType(30);
                    this.sceneStatisticsService.saveOrUpdateFilingStatistics(sceneStatistics);
                }

                //带看 DAY
                List<Map<String, Object>> dayVistStatistics = sceneStatisticsService.queryVisitByDay();
                for (Map<String, Object> m : dayVistStatistics) {
                    int sceneId = Integer.valueOf(m.get("sceneId").toString()) ;
                    Map<String, Object> mapId = sceneStatisticsService.findDayByCreateAt(sceneId) ;
                    sceneStatistics = getSceneStatistics(mapId) ;
                    int visitStatistics = Integer.valueOf(m.get("visitStatistics").toString()) ;
                    sceneStatistics.setSceneId(sceneId);
                    sceneStatistics.setVisitStatistics(visitStatistics);
                    sceneStatistics.setType(1);
                    this.sceneStatisticsService.saveOrUpdateFilingStatistics(sceneStatistics);
                }

                //带看 MONTH
                List<Map<String, Object>> monthVisitStatistics = sceneStatisticsService.queryVisitByMonth();
                for (Map<String, Object> m : monthVisitStatistics) {
                    int sceneId = Integer.valueOf(m.get("sceneId").toString()) ;
                    Map<String, Object> mapId = sceneStatisticsService.findMonthByCreateAt(sceneId) ;
                    sceneStatistics = getSceneStatistics(mapId) ;
                    int visitStatistics = Integer.valueOf(m.get("visitStatistics").toString()) ;
                    sceneStatistics.setSceneId(sceneId);
                    sceneStatistics.setVisitStatistics(visitStatistics);
                    sceneStatistics.setType(30);
                    this.sceneStatisticsService.saveOrUpdateFilingStatistics(sceneStatistics);
                }

                // 核销 DAY
                List<Map<String, Object>> dayDealStatistics = sceneStatisticsService.queryDealByDay();
                for (Map<String, Object> m : dayDealStatistics) {
                    int sceneId = Integer.valueOf(m.get("sceneId").toString()) ;
                    Map<String, Object> mapId = sceneStatisticsService.findDayByCreateAt(sceneId) ;
                    sceneStatistics = getSceneStatistics(mapId) ;
                    int dealStatistics = Integer.valueOf(m.get("dealStatistics").toString()) ;
                    sceneStatistics.setSceneId(sceneId);
                    sceneStatistics.setDealStatistics(dealStatistics);
                    sceneStatistics.setType(1);
                    this.sceneStatisticsService.saveOrUpdateFilingStatistics(sceneStatistics);
                }

                // 核销 MONTH
                List<Map<String, Object>> monthDealStatistics = sceneStatisticsService.queryDealByMonth();
                for (Map<String, Object> m : monthDealStatistics) {
                    int sceneId = Integer.valueOf(m.get("sceneId").toString()) ;
                    Map<String, Object> mapId = sceneStatisticsService.findMonthByCreateAt(sceneId) ;
                    sceneStatistics = getSceneStatistics(mapId) ;
                    int dealStatistics = Integer.valueOf(m.get("dealStatistics").toString()) ;
                    sceneStatistics.setSceneId(sceneId);
                    sceneStatistics.setDealStatistics(dealStatistics);
                    sceneStatistics.setType(30);
                    this.sceneStatisticsService.saveOrUpdateFilingStatistics(sceneStatistics);
                }

            } catch (Exception e) {
                LOGGER.error("案场数据统计分析任务异常",e);
            }
            LOGGER.info("===================案场数据统计分析任务end===================耗时=" + DateUtil.calIntervalSec(start,System.currentTimeMillis()));
        }
    }

    /**
     *  map to SceneStatistics
     * @param mapId
     * @return
     */
    private SceneStatistics getSceneStatistics(Map<String, Object> mapId) {
        SceneStatistics sceneStatistics = new SceneStatistics() ;

        if (mapId != Collections.EMPTY_MAP) {
            int id = Integer.valueOf(mapId.get("id").toString()) ;
            sceneStatistics.setId(id);
            if (mapId.get("createAt") != null) {
                Date  createAt = (Date) mapId.get("createAt");
                sceneStatistics.setCreateAt(createAt);
            }
            if (mapId.get("updateAt") != null) {
                Date  updateAt = (Date) mapId.get("updateAt");
                sceneStatistics.setUpdateAt(updateAt);
            }
            if (mapId.get("version") != null) {
                Integer  version =Integer.valueOf(mapId.get("version").toString()) ;
                sceneStatistics.setVersion(version);
            }
            if (mapId.get("filingStatistics") != null) {
                Integer  filingStatistics =Integer.valueOf(mapId.get("filingStatistics").toString()) ;
                sceneStatistics.setFilingStatistics(filingStatistics);
            }
            if (mapId.get("visitStatistics") != null) {
                Integer  visitStatistics =Integer.valueOf(mapId.get("visitStatistics").toString()) ;
                sceneStatistics.setVisitStatistics(visitStatistics);
            }
            if (mapId.get("dealStatistics") != null) {
                Integer  dealStatistics =Integer.valueOf(mapId.get("dealStatistics").toString()) ;
                sceneStatistics.setDealStatistics(dealStatistics);
            }
            if (mapId.get("type") != null) {
                Integer  type =Integer.valueOf(mapId.get("type").toString()) ;
                sceneStatistics.setType(type);
            }
        }
         return sceneStatistics ;
    }
}
