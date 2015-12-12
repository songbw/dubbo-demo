package com.flzc.scene.filing.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.base.util.DateUtil;
import com.flzc.scene.filing.api.entity.SceneStatistics;
import com.flzc.scene.filing.api.service.SceneStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 *  数据统计服务实现
 * Created by song on 2015/12/2.
 */
@Service("sceneStatisticsService")
public class SceneStatisticsServiceImpl extends BaseServiceImpl implements SceneStatisticsService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneStatisticsServiceImpl.class);
    /**
     * 查询周数据
     *
     * @param sceneId
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public List<SceneStatistics> queryWeek(Integer sceneId, long fromDate, long toDate) {
        Date from  = new Date(fromDate) ;
        String fromdate = DateUtil.format(from, "yyyy-MM-dd");
        Date to  = new Date(toDate) ;
        String todate = DateUtil.format(to, "yyyy-MM-dd");
        String hql = "from SceneStatistics  where sceneId = "+ sceneId +" and type = 1 and createAt  between '" + fromdate + "' AND '" + todate+"'" ;
        List<SceneStatistics> list = this.findByHql(hql);
        return list;
    }

    /**
     * 查询月数据（1，5，10，15，20，25，30，31）
     *
     * @param sceneId
     * @param monthDate
     * @return
     */
    @Override
    public List<SceneStatistics> queryMonth(Integer sceneId, long monthDate) {
        Date month  = new Date(monthDate) ;
        String monthdate = DateUtil.format(month, "yyyy-MM");
        String hql = "from SceneStatistics  where sceneId = "+ sceneId +" and type = 1 and DATE_FORMAT(createAt,'%Y-%m-%d')" +
                " in  ('"+monthdate +"-01','"+monthdate + "-05','"+monthdate + "-10','"+monthdate+"-15','"+monthdate+"-20','"+monthdate +
                "-25','"+monthdate+"-30','"+monthdate+"-31'"+")" ;
        List<SceneStatistics> list = this.findByHql(hql);
        return list;
    }

    /**
     * 查询年数据（1，2，3，4，5，6，7，8，9，10，11，12）
     *
     * @param sceneId
     * @param yearDate
     * @return
     */
    @Override
    public List<SceneStatistics> queryYear(Integer sceneId, long yearDate) {
        Date year  = new Date(yearDate) ;
        String yeardate = DateUtil.format(year, "yyyy");
        String hql = "from SceneStatistics  where sceneId = "+ sceneId +" and type = 30 and DATE_FORMAT(createAt,'%Y') = '" + yeardate +"'" ;
        List<SceneStatistics> list = this.findByHql(hql);
        return list;
    }

    /**
     * 周总数
     *
     * @param sceneId
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public SceneStatistics queryWeekTotal(Integer sceneId, long fromDate, long toDate) {
        Date from  = new Date(fromDate) ;
        String fromdate = DateUtil.format(from, "yyyy-MM-dd hh:mm:ss");
        Date to  = new Date(toDate) ;
        String todate = DateUtil.format(to, "yyyy-MM-dd hh:mm:ss");
        String sql = "SELECT scene_id, SUM(filing_statistics) as filingStatistics ,SUM(visit_statistics) as visitStatistics,SUM(deal_statistics) as dealStatistics" +
                " from scene_statistics WHERE create_at >='"+fromdate+"' AND  create_at<='"+todate+"'AND scene_id = "+sceneId+" AND type = 1 GROUP BY scene_id;" ;
        Map map = this.findKeyMapBySql(sql) ;
        SceneStatistics sceneStatistics = new SceneStatistics();
        HashMap<String,Integer> hashMap = (HashMap<String, Integer>) map.get(null);
        if (hashMap == null) {
            return  null ;
        }
        Iterator<Map.Entry<String, Integer>> entries = hashMap.entrySet().iterator() ;
        while (entries.hasNext()) {
            Map.Entry<String, ?> entry = entries.next();
            if ("visitStatistics".equals(entry.getKey())) {
                BigDecimal bigInteger = (BigDecimal) entry.getValue();
                int a = Integer.valueOf(bigInteger.toString()) ;
                sceneStatistics.setVisitStatistics(a);
            }
            if ("filingStatistics".equals(entry.getKey())) {
                BigDecimal bigInteger = (BigDecimal) entry.getValue();
                int a = Integer.valueOf(bigInteger.toString()) ;
                sceneStatistics.setFilingStatistics(a);
            }
            if ("scene_id".equals(entry.getKey())) {
                Integer bigInteger = (Integer) entry.getValue();
                sceneStatistics.setSceneId(bigInteger);
            }
            if ("dealStatistics".equals(entry.getKey())) {
                BigDecimal bigInteger = (BigDecimal) entry.getValue();
                int a = Integer.valueOf(bigInteger.toString()) ;
                sceneStatistics.setDealStatistics(a);
            }
        }
        return sceneStatistics;
    }

    /**
     * 月总是
     *
     * @param sceneId
     * @param monthDate
     * @return
     */
    @Override
    public SceneStatistics queryMonthTotal(Integer sceneId, long monthDate) {
        Date month  = new Date(monthDate) ;
        String monthdate = DateUtil.format(month, "yyyy-MM");
        String hql = "from SceneStatistics  where sceneId = "+ sceneId +" and type = 30 and DATE_FORMAT(createAt,'%Y-%m') = '" + monthdate +"'";
        List<SceneStatistics> list = this.findByHql(hql);
        if (list == null || list.size() == 0) {
            return null ;
        }
        return list.get(0);
    }

    /**
     * 年总数
     *
     * @param sceneId
     * @param yearDate
     * @return
     */
    @Override
    public SceneStatistics queryYearTotal(Integer sceneId, long yearDate) {
        Date year  = new Date(yearDate) ;
        String yeardate = DateUtil.format(year, "yyyy");
        String sql = "SELECT scene_id, SUM(filing_statistics) as filingStatistics ,SUM(visit_statistics) as visitStatistics,SUM(deal_statistics) as dealStatistics" +
                " from scene_statistics WHERE DATE_FORMAT(create_at,'%Y') = '"+yeardate+"' AND scene_id = "+sceneId+" AND type = 30 GROUP BY scene_id;" ;
        Map map = this.findKeyMapBySql(sql) ;
        SceneStatistics sceneStatistics = new SceneStatistics();
        HashMap<?,?> hashMap = (HashMap<?, ?>) map.get(null);
        Iterator<? extends Map.Entry<?, ?>> entries = hashMap.entrySet().iterator() ;
        while (entries.hasNext()) {
            Map.Entry<?, ?> entry = entries.next();
            if ("visitStatistics".equals(entry.getKey())) {
                BigDecimal bigInteger = (BigDecimal) entry.getValue();
                int a = Integer.valueOf(bigInteger.toString()) ;
                sceneStatistics.setVisitStatistics(Integer.valueOf(bigInteger.toString()));
            }
            if ("filingStatistics".equals(entry.getKey())) {
                BigDecimal bigInteger = (BigDecimal) entry.getValue();
                int a = Integer.valueOf(bigInteger.toString()) ;
                sceneStatistics.setFilingStatistics(Integer.valueOf(bigInteger.toString()));
            }
            if ("scene_id".equals(entry.getKey())) {
                Integer bigInteger = (Integer) entry.getValue();
                sceneStatistics.setSceneId(bigInteger);
            }
            if ("dealStatistics".equals(entry.getKey())) {
                BigDecimal bigInteger = (BigDecimal) entry.getValue();
                int a = Integer.valueOf(bigInteger.toString()) ;
                sceneStatistics.setDealStatistics(Integer.valueOf(bigInteger.toString()));
            }
        }
        return sceneStatistics;
    }
}
