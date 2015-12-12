package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.entity.AgencyOfficeDayStatistics;
import com.flzc.quartz.entity.AgencyOfficeHistoryStatistics;
import com.flzc.quartz.entity.AgencyOfficeMonthStatistics;
import com.flzc.quartz.entity.AgencyOfficeWeekStatistics;
import com.flzc.quartz.service.AgencyOfficeStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 */
@Service("agencyOfficeStatisticsService")
public class AgencyOfficeStatisticsServiceImpl implements AgencyOfficeStatisticsService {
    @Autowired
    private CommonDao commonDao;

    @Override
    public  List<Map<String, Object>> queryAllOffice(){
        String sql = "select office_code as officeCode from agency_office";
        List<Map<String, Object>> office = this.commonDao.findBySql(sql);
        if(office==null) return Collections.EMPTY_LIST;
        return office;
    }


    @Override
    public void save(AgencyOfficeDayStatistics statistics) throws Exception {
        statistics.setCreateTime(new Date());
        statistics.setStatus(0);
        this.commonDao.save(statistics);
    }
    @Override
    public void save(AgencyOfficeWeekStatistics statistics) throws Exception {
        this.commonDao.save(statistics);
    }

    @Override
    public void save(AgencyOfficeMonthStatistics statistics) throws Exception {
        statistics.setCreateTime(new Date());
        statistics.setStatus(0);
        this.commonDao.save(statistics);
    }

    @Override
    public void save(AgencyOfficeHistoryStatistics statistics) throws Exception {
        statistics.setCreateTime(new Date());
        statistics.setStatus(0);
        this.commonDao.save(statistics);
    }

    @Override
    public void updateAllDayStatsInvalid() {
        String sql = "update agency_office_day_statistics set status=1 , update_time=now() where status=0";
        this.commonDao.updateBySql(sql);
    }

    @Override
    public void updateAllWeekStatsInvalid() {
        String sql = "update agency_office_week_statistics set status=1 , update_time=now() where status=0";
        this.commonDao.updateBySql(sql);
    }

    @Override
    public void updateAllMonthStatsInvalid() {
        String sql = "update agency_office_month_statistics set status=1 , update_time=now() where status=0";
        this.commonDao.updateBySql(sql);
    }

    @Override
    public void updateAllHistoryStatsInvalid() {
        String sql = "update agency_office_history_statistics set status=1 , update_time=now() where status=0";
        this.commonDao.updateBySql(sql);
    }

    private Map<String,Object> list2Map(List<Map<String, Object>> rows){
        Map<String,Object> result = new HashMap<>();
        if(rows == null){
            return result;
        }
        for (Map<String,Object> m : rows){
            Object officeCode = m.get("officeCode");
            if(officeCode != null){
                Object count = m.get("count");
                count = (count==null?0:count);
                result.put(officeCode.toString(),count);
            }
        }
        return result;
    }

    /**
     * 查询所有门店当天报备
     * @return
     */
    @Override
    public Map<String, Object> queryReportDayStat() {
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and DATE_FORMAT(i.create_time,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') " +
                "and i.status in (57003 ,57004,57005,57006,57009,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);

    }

    @Override
    public Map<String,Object> queryVisitDayStat(){
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and DATE_FORMAT(i.create_time,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') " +
                "and i.status in ( 57004,57005,57006,57009,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);
    }
    @Override
    public Map<String,Object> queryDealDayStat(){
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and DATE_FORMAT(i.create_time,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') " +
                "and i.status in ( 57005,57006,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);
    }
    @Override
    public Map<String,Object> queryCommissionDayStat(){
        String sql = "SELECT " +
                " sum(c.amount) as count, " +
                " a.office_id AS officeCode " +
                "FROM " +
                " `agency_commission` c, " +
                " agency a " +
                "where  c.status = 1 and c.agency_id = a.id  " +
                "and DATE_FORMAT(c.create_time,'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d') " +
                "GROUP BY a.office_id ";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return   list2Map(rows);
    }

/*************************************************/

    @Override
    public Map<String, Object> queryReportWeekStat() {
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and weekofyear(i.create_time) = weekofyear(now()) " +
                "and i.status in (57003 ,57004,57005,57006,57009,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return  list2Map(rows);
    }

    @Override
    public Map<String,Object> queryVisitWeekStat(){
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and weekofyear(i.create_time) = weekofyear(now()) " +
                "and i.status in ( 57004,57005,57006,57009,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);
    }

    @Override
    public Map<String,Object> queryDealWeekStat(){
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and weekofyear(i.create_time) = weekofyear(now()) " +
                "and i.status in ( 57005,57006,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);
    }

    @Override
    public Map<String,Object> queryCommissionWeekStat(){
        String sql = "SELECT " +
                " sum(c.amount) as count, " +
                " a.office_id AS officeCode " +
                "FROM " +
                " `agency_commission` c, " +
                " agency a " +
                "where  c.status = 1 and c.agency_id = a.id  " +
                "and weekofyear(i.create_time) = weekofyear(now()) " +
                "GROUP BY a.office_id ";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return   list2Map(rows);
    }

  /******************************/

    @Override
    public Map<String, Object> queryReportMonthStat() {
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and month(i.create_time) = month(now()) " +
                "and i.status in (57003 ,57004,57005,57006,57009,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
       return list2Map(rows);
    }

    @Override
    public Map<String,Object> queryVisitMonthStat(){
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and month(i.create_time) = month(now()) " +
                "and i.status in ( 57004,57005,57006,57009,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);
    }

    @Override
    public Map<String,Object> queryDealMonthStat(){
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and month(i.create_time) = month(now()) " +
                "and i.status in ( 57005,57006,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);
    }

    @Override
    public Map<String,Object> queryCommissionMonthStat(){
        String sql = "SELECT " +
                " sum(c.amount) as count, " +
                " a.office_id AS officeCode " +
                "FROM " +
                " `agency_commission` c, " +
                " agency a " +
                "where  c.status = 1 and c.agency_id = a.id  " +
                "and month(i.create_time) = month(now()) " +
                "GROUP BY a.office_id ";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return   list2Map(rows);
    }


    /*************************************************/
    @Override
    public Map<String, Object> queryReportHistoryStat() {
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and i.status in (57003 ,57004,57005,57006,57009,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return list2Map(rows);
    }


    @Override
    public Map<String,Object> queryVisitHistoryStat(){
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and i.status in ( 57004,57005,57006,57009,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);
    }

    @Override
    public Map<String,Object> queryDealHistoryStat(){
        String sql = "SELECT " +
                " count(i.id) as count , " +
                "  a.office_id as officeCode " +
                "FROM " +
                " agency_client_intention_building i, " +
                " agency_client c, " +
                " agency a " +
                "WHERE " +
                " i.client_id = c.id " +
                "AND c.agency_id = a.id " +
                "and i.status in ( 57005,57006,57010) " +
                "GROUP BY a.office_id ";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return    list2Map(rows);
    }

    @Override
    public Map<String,Object> queryCommissionHistoryStat(){
        String sql = "SELECT " +
                " sum(c.amount) as count, " +
                " a.office_id AS officeCode " +
                "FROM " +
                " `agency_commission` c, " +
                " agency a " +
                "where  c.status = 1 and c.agency_id = a.id  " +
                "GROUP BY a.office_id ";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        return   list2Map(rows);
    }

}
