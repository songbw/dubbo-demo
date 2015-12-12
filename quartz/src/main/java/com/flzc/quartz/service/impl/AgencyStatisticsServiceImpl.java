package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.entity.AgencyReportCount;
import com.flzc.quartz.entity.AgencySeeCount;
import com.flzc.quartz.service.AgencyStatisticsService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.awt.SunToolkit;

import java.util.*;

/**
 * Created by iverson on 2015/11/26.
 */
@Service("agencyStatisticsService")
public class AgencyStatisticsServiceImpl implements AgencyStatisticsService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(AgencyStatisticsServiceImpl.class);
    @Autowired
    private CommonDao commonDao;

    /**
     * 按天统计
     *
     * @return
     */
    @Override
    public List<Map<String,Object>> queryReportByDay() {
        String sql ="SELECT " +
                " a.agency_id as agencyId, " +
                " COUNT(a.id) as total " +
                "FROM " +
                " agency_client_intention_building a " +
                "where DATE_FORMAT(a.update_time,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') and a.status in  (57003,57004,57005,57006,57009,57010) " +
                "GROUP BY " +
                " a.agency_id " +
                "ORDER BY " +
                " a.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;

    }

    @Override
    public List<Map<String,Object>> queryReportByWeek() {
        String sql ="SELECT " +
                " a.agency_id as agencyId, " +
                " COUNT(a.id) as total " +
                "FROM " +
                " agency_client_intention_building a " +
                " where a.status in  (57003,57004,57005,57006,57009,57010) " +
                " and weekofyear(a.update_time) = weekofyear(now()) " +
                "GROUP BY " +
                " a.agency_id " +
                "ORDER BY " +
                " a.agency_id";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    @Override
    public List<Map<String,Object>> queryReportByMonth() {

        String sql ="SELECT " +
                " a.agency_id as agencyId, " +
                " COUNT(a.id) as total " +
                "FROM " +
                " agency_client_intention_building a " +
                " where   a.status  in  (57003,57004,57005,57006,57009,57010) " +
                " and  month(a.update_time) =month(now())  " +
                " GROUP BY " +
                " a.agency_id " +
                " ORDER BY " +
                " a.agency_id";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    /**
     * 更新数据
     * @param stat
     * @throws Exception
     */
    @Override
   public void saveOrUpdateStatistics(AgencyReportCount stat) throws Exception {
        Integer id = stat.getAgencyId();
        AgencyReportCount param = new AgencyReportCount();
        param.setAgencyId(id);
        AgencyReportCount old = this.commonDao.findUniqueObj(param);
        if(old == null){
            Date now = new Date();
            stat.setCreateTime(now);
            stat.setUpdateTime(now);
            this.commonDao.save(stat);
            return ;
        }
        old.setDayCount(stat.getDayCount());
        old.setWeekCount(stat.getWeekCount());
        old.setMonthCount(stat.getMonthCount());
        old.setTotalCount(stat.getTotalCount());
        old.setUpdateTime(new Date());
        this.commonDao.update(old);

   }

    /**
     * 带看
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> querySeeByDay() {
        String sql ="SELECT " +
                " a.agency_id as agencyId, " +
                " COUNT(a.id) as total " +
                "FROM " +
                " agency_client_intention_building a " +
                "where DATE_FORMAT(a.update_time,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') and a.status in ( 57004,57005,57006,57009,57010 ) " +
                "GROUP BY " +
                " a.agency_id " +
                "ORDER BY " +
                " a.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    @Override
    public List<Map<String, Object>> querySeeByWeek() {
        String sql ="SELECT " +
                " a.agency_id as agencyId, " +
                " COUNT(a.id) as total " +
                "FROM " +
                " agency_client_intention_building a " +
                " where a.status in ( 57004,57005,57006,57009,57010 ) and  " +
                "  weekofyear(a.update_time) =  weekofyear(now()) " +
                "GROUP BY " +
                " a.agency_id " +
                "ORDER BY " +
                " a.agency_id";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    @Override
    public List<Map<String, Object>> querySeeByMonth() {
        String sql ="SELECT " +
                " a.agency_id as agencyId, " +
                " COUNT(a.id) as total " +
                "FROM " +
                " agency_client_intention_building a " +
                " where a.status in ( 57004,57005,57006,57009,57010 ) " +
                "  and  month(a.update_time) = month(now())  " +
                " GROUP BY " +
                " a.agency_id " +
                " ORDER BY " +
                " a.agency_id";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.emptyList();
        return rows;
    }

    @Override
    public void saveOrUpdateStatistics(AgencySeeCount stat) throws Exception {
        Integer id = stat.getAgencyId();
        AgencySeeCount param = new AgencySeeCount();
        param.setAgencyId(id);
        AgencySeeCount old = this.commonDao.findUniqueObj(param);
        if(old == null){
            Date now = new Date();
            stat.setCreateTime(now);
            stat.setUpdateTime(now);
            this.commonDao.save(stat);
            return ;
        }
        old.setDayCount(stat.getDayCount());
        old.setWeekCount(stat.getWeekCount());
        old.setMonthCount(stat.getMonthCount());
        old.setTotalCount(stat.getTotalCount());
        old.setUpdateTime(new Date());
        this.commonDao.update(old);
    }

    /**
     * 查询所有经纪人带看总数
     * @return
     */
    @Override
    public List<Map<String, Object>> querySeeTotal(){
        String sql = "SELECT " +
                " a.agency_id as agencyId, " +
                " COUNT(a.id) as total " +
                "FROM " +
                " agency_client_intention_building a " +
                "where  a.`status` in ( 57004,57005,57006,57009,57010 ) " +
                "GROUP BY " +
                " a.agency_id " ;
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows == null) return Collections.EMPTY_LIST;
        return rows;
    }

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().get(Calendar.MONTH));
    }
}
