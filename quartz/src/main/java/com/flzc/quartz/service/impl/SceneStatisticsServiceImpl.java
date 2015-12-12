package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.entity.SceneStatistics;
import com.flzc.quartz.service.SceneStatisticsService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 案场数据统计服务  日，周，月
 * Created by song on 2015/12/1.
 */
@Service("sceneStatisticsService")
public class SceneStatisticsServiceImpl implements SceneStatisticsService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneStatisticsServiceImpl.class);
    @Autowired
    private CommonDao commonDao;
    /**
     * 报备统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryFilingByDay() {
        String sql = "SELECT" +
                " scene_id as sceneId,COUNT(scene_id) as filingStatistics" +
                " FROM scene_filing" +
                " WHERE  DATE_FORMAT(update_at,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') AND status = 57003" +
                " GROUP BY scene_id" ;
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    @Override
    public List<Map<String, Object>> queryFilingByMonth() {
        String sql = "SELECT" +
                " scene_id as sceneId,COUNT(scene_id) as filingStatistics" +
                " FROM scene_filing " +
                "WHERE  DATE_FORMAT(update_at,'%Y%m') = DATE_FORMAT(now(),'%Y%m') AND status = 57003" +
                " GROUP BY scene_id" ;
        String now = DateUtil.getCurrenDate();
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    @Override
    public void saveOrUpdateFilingStatistics(SceneStatistics sceneStatistics) throws Exception {
        if(sceneStatistics.getId() == null || sceneStatistics.getId() == 0){
            sceneStatistics.setCreateAt(new Date());
            this.commonDao.save(sceneStatistics);
        }else {
            sceneStatistics.setUpdateAt(new Date());
            this.commonDao.update(sceneStatistics);
        }
    }

    @Override
    public Map<String,Object> findDayByCreateAt(Integer sceneId) {
        String sql = "SELECT id, scene_id as sceneId," +
                " create_at as createAt," +
                " type, update_at as updateAt," +
                " version," +
                "filing_statistics as filingStatistics," +
                " visit_statistics as visitStatistics," +
                " deal_statistics as dealStatistics" +
                " FROM scene_statistics" +
                " WHERE DATE_FORMAT(create_at,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') AND type = 1 AND scene_id = " + sceneId ;
        Map<String, Object> rows = this.commonDao.findUniqueBySql(sql);
        if(rows == null) return Collections.EMPTY_MAP;
        return rows;
    }

    @Override
    public Map<String, Object> findMonthByCreateAt(Integer sceneId) {
        String sql = "SELECT  id, scene_id as sceneId," +
                " create_at as createAt," +
                " type, update_at as updateAt," +
                " version," +
                "filing_statistics as filingStatistics," +
                " visit_statistics as visitStatistics," +
                " deal_statistics as dealStatistics" +
                " FROM scene_statistics" +
                " WHERE DATE_FORMAT(create_at,'%Y%m') = DATE_FORMAT(now(),'%Y%m') AND type = 30 AND scene_id= " + sceneId ;
        Map<String, Object> rows = this.commonDao.findUniqueBySql(sql);
        if(rows == null) return Collections.EMPTY_MAP;
        return rows;
    }

    /**
     * 看房统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryVisitByDay() {
        String sql = "SELECT" +
                " scene_id as sceneId,COUNT(scene_id) as visitStatistics" +
                " FROM scene_filing " +
                "WHERE  DATE_FORMAT(update_at,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') AND status = 57004" +
                " GROUP BY scene_id" ;
        String now = DateUtil.getCurrenDate();
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    @Override
    public List<Map<String, Object>> queryVisitByMonth() {
        String sql = "SELECT" +
                " scene_id as sceneId,COUNT(scene_id) as visitStatistics" +
                " FROM scene_filing " +
                "WHERE  DATE_FORMAT(update_at,'%Y%m') = DATE_FORMAT(now(),'%Y%m') AND status = 57004" +
                " GROUP BY scene_id" ;
        String now = DateUtil.getCurrenDate();
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    /**
     * 核销统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDealByDay() {
        String sql = "SELECT" +
                " scene_id as sceneId,COUNT(scene_id) as dealStatistics" +
                " FROM scene_filing " +
                "WHERE  DATE_FORMAT(update_at,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') AND status = 57005" +
                " GROUP BY scene_id" ;
        String now = DateUtil.getCurrenDate();
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    @Override
    public List<Map<String, Object>> queryDealByMonth() {
        String sql = "SELECT" +
                " scene_id as sceneId,COUNT(scene_id) as dealStatistics" +
                " FROM scene_filing " +
                "WHERE  DATE_FORMAT(update_at,'%Y%m') = DATE_FORMAT(now(),'%Y%m') AND status = 57005" +
                " GROUP BY scene_id" ;
        String now = DateUtil.getCurrenDate();
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }
}
