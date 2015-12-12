package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.entity.*;
import com.flzc.quartz.service.AgencyContributeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 经纪人业绩汇总服务接口
 */
@Service("agencyContributeService")
public class AgencyContributeServiceImpl implements AgencyContributeService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(AgencyContributeServiceImpl.class);

    @Autowired
    private CommonDao commonDao;


    @Override
    public void saveOrUpdate(AgencyContribution contribution) throws Exception {
        contribution.setCreateTime(new Date());
        contribution.setStatus(0);
        this.commonDao.save(contribution);
    }

    @Override
    public void deleteAll() throws Exception {
        String hql = "update AgencyContribution set status=1 , update_time=now() where status=0";
        this.commonDao.updateByHql(hql, null, null);
    }

    @Override
    public void saveOrUpdateDay(AgencyDayContribution contribution) throws Exception {
        contribution.setCreateTime(new Date());
        contribution.setStatus(0);
        this.commonDao.save(contribution);
    }
    @Override
    public void deleteDayAll() throws Exception {
        String hql = "update AgencyDayContribution set status=1 , update_time=now() where status=0";
        this.commonDao.updateByHql(hql, null, null);
    }

    @Override
    public void saveOrUpdateWeek(AgencyWeekContribution contribution) throws Exception {
        contribution.setCreateTime(new Date());
        contribution.setStatus(0);
        this.commonDao.save(contribution);
    }
    @Override
    public void deleteWeekAll() throws Exception {
        String hql = "update AgencyWeekContribution set status=1 , update_time=now()  where status=0";
        this.commonDao.updateByHql(hql, null, null);
    }

    @Override
    public void saveOrUpdateMonth(AgencyMonthContribution contribution) throws Exception {
        contribution.setCreateTime(new Date());
        contribution.setStatus(0);
        this.commonDao.save(contribution);
    }
    @Override
    public void deleteMonthAll() throws Exception {
        String hql = "update AgencyMonthContribution set status=1 , update_time=now() where status=0";
        this.commonDao.updateByHql(hql, null, null);
    }




    /**
     * 查询出所有的
     *
     * @return
     */
    @Override
    public List<Agency> queryAllAgency() throws Exception {
        Agency param = new Agency();
        param.setStatus(0);
        List<Agency> agencies = this.commonDao.findObjs(param);
        if(agencies == null){
            agencies = Collections.EMPTY_LIST;
        }
        return agencies;
    }

    private Map<String,Object> list2Map(List<Map<String, Object>> rows){
        Map<String,Object> result = new HashMap<>();
        if(rows == null){
            return result;
        }
        for (Map<String,Object> m : rows){
            Object agencyId = m.get("agencyId");
            if(agencyId != null){
                Object count = m.get("total");
                count = (count==null?0:count);
                result.put(agencyId.toString(),count);
            }
        }
        return result;
    }
    /**************** 按周查询报备，带看，成交，佣金 ***************/
    @Override
    public Map<String, Object> queryReportWeek(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  WEEKOFYEAR(b.update_time) = WEEKOFYEAR(now()) " +
                " b.status in (57003 ,57004,57005,57006,57009,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);

    }
    @Override
    public Map<String, Object> queryVisitWeek(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  WEEKOFYEAR(b.update_time) = WEEKOFYEAR(now()) " +
                " b.status in (57004,57005,57006,57009,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);

    }
    @Override
    public Map<String, Object> queryDealWeek(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  WEEKOFYEAR(b.update_time) = WEEKOFYEAR(now()) " +
                " b.status in (57005,57006,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);
    }
    @Override
    public Map<String, Object> queryCommissionWeek(){
        String sql = "SELECT " +
                " sum(c.amount) AS total, " +
                " c.agency_id AS agencyId " +
                "FROM " +
                " agency_commission c " +
                "where c.`status` = 1 and   WEEKOFYEAR(c.update_time)=WEEKOFYEAR(now()) " +
                "GROUP BY " +
                " c.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);
    }

    /****************** 按月统计  ***********************/

    @Override
    public Map<String, Object> queryReportMonth(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  month(b.update_time) = month(now()) " +
                " b.status in (57003 ,57004,57005,57006,57009,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);

    }
    @Override
    public Map<String, Object> queryVisitMonth(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  month(b.update_time) = month(now()) " +
                " b.status in (57004,57005,57006,57009,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);

    }
    @Override
    public Map<String, Object> queryDealMonth(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  month(b.update_time) = month(now()) " +
                " b.status in (57005,57006,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);
    }
    @Override
    public Map<String, Object> queryCommissionMonth(){
        String sql = "SELECT " +
                " sum(c.amount) AS total, " +
                " c.agency_id AS agencyId " +
                "FROM " +
                " agency_commission c " +
                "where c.`status` = 1 and   month(c.update_time)=month(now()) " +
                "GROUP BY " +
                " c.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);
    }

    /********************* 按天 ***************************/

    @Override
    public Map<String, Object> queryReportDay(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  date_format(b.update_time,'%Y%m%d') = date_format(now(),'%Y%m%d') " +
                " b.status in (57003 ,57004,57005,57006,57009,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);

    }
    @Override
    public Map<String, Object> queryVisitDay(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  date_format(b.update_time,'%Y%m%d') = date_format(now(),'%Y%m%d') " +
                " b.status in (57004,57005,57006,57009,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);

    }
    @Override
    public Map<String, Object> queryDealDay(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                "  date_format(b.update_time,'%Y%m%d') = date_format(now(),'%Y%m%d') " +
                " b.status in (57005,57006,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);
    }
    @Override
    public Map<String, Object> queryCommissionDay(){
        String sql = "SELECT " +
                " sum(c.amount) AS total, " +
                " c.agency_id AS agencyId " +
                "FROM " +
                " agency_commission c " +
                "where c.`status` = 1 and  date_format(b.update_time,'%Y%m%d') = date_format(now(),'%Y%m%d') " +
                "GROUP BY " +
                " c.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);
    }

   /*********************** 所有 *********************************/
   @Override
   public Map<String, Object> queryReportAll(){
       String sql = "SELECT " +
               " count(b.id) AS total, " +
               " b.agency_id as agencyId " +
               "FROM " +
               " agency_client_intention_building b " +
               "WHERE " +
               " b.status in (57003 ,57004,57005,57006,57009,57010) " +
               "GROUP BY " +
               " b.agency_id";
       List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
       if(rows==null) return Collections.EMPTY_MAP;
       return list2Map(rows);

   }
    @Override
    public Map<String, Object> queryVisitAll(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                " b.status in (57004,57005,57006,57009,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);

    }
    @Override
    public Map<String, Object> queryDealAll(){
        String sql = "SELECT " +
                " count(b.id) AS total, " +
                " b.agency_id as agencyId " +
                "FROM " +
                " agency_client_intention_building b " +
                "WHERE " +
                " b.status in (57005,57006,57010) " +
                "GROUP BY " +
                " b.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);
    }
    @Override
    public Map<String, Object> queryCommissionAll(){
        String sql = "SELECT " +
                " sum(c.amount) AS total, " +
                " c.agency_id AS agencyId " +
                "FROM " +
                " agency_commission c " +
                "where c.`status` = 1 " +
                "GROUP BY " +
                " c.agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_MAP;
        return list2Map(rows);
    }



    /**
     * 按天统计
     * @param agencies
     * @throws Exception
     */
    @Override
    public void saveStatisticsDay(List<Agency> agencies) throws Exception {
        this.deleteDayAll();
        int errorAgency = 0;
        int agencyTotal = agencies.size();
        Map<String, Object> commission = this.queryCommissionDay();
        Map<String, Object> reportDay = this.queryReportDay();
        Map<String, Object> visitDay = this.queryVisitDay();
        Map<String, Object> dealDay = this.queryDealDay();
        AgencyDayContribution con ;
        for (Agency agency : agencies){
            String id = agency.getId().toString();
            con = new AgencyDayContribution();
            try {
                Object reportNum = reportDay.get(id);
                Object visitNum = visitDay.get(id);
                Object dealNum = dealDay.get(id);
                Object comm = commission.get(id);
                con.setAgencyId(agency.getId());
                con.setReportNum(Integer.valueOf(reportNum == null ? "0" : reportNum.toString()));
                con.setVisitNum(Integer.valueOf(visitNum == null ? "0" : visitNum.toString()));
                con.setDealNum(Integer.valueOf(dealNum == null ? "0" : dealNum.toString()));
                con.setCommission(Integer.valueOf(comm == null ? "0" : comm.toString()));
                this.saveOrUpdateDay(con);
            } catch (Exception e) {
                errorAgency++;
                LOGGER.error(String.format("经纪人[id=%1s]业绩汇总异常",id), e);
            }
        }
        LOGGER.info( String.format("经纪人按天统计业绩完成：总数%1s,失败%2s",agencyTotal,errorAgency ) );
    }

    /**
     * 按周统计
     * @param agencies
     * @throws Exception
     */
    @Override
    public void saveStatisticsWeek(List<Agency> agencies) throws Exception {
        this.deleteWeekAll();
        int errorAgency = 0;
        int agencyTotal = agencies.size();
        Map<String, Object> commission = this.queryCommissionWeek();
        Map<String, Object> reportWeek = this.queryReportWeek();
        Map<String, Object> visitWeek = this.queryVisitWeek();
        Map<String, Object> dealWeek = this.queryDealWeek();
        AgencyWeekContribution con ;
        for (Agency agency : agencies){
            String id = agency.getId().toString();
            con = new AgencyWeekContribution();
            try {
                Object reportNum = reportWeek.get(id);
                Object visitNum = visitWeek.get(id);
                Object dealNum = dealWeek.get(id);
                Object comm = commission.get(id);
                con.setAgencyId(agency.getId());
                con.setReportNum(Integer.valueOf(reportNum == null ? "0" : reportNum.toString()));
                con.setVisitNum(Integer.valueOf(visitNum == null ? "0" : visitNum.toString()));
                con.setDealNum(Integer.valueOf(dealNum == null ? "0" : dealNum.toString()));
                con.setCommission(Integer.valueOf(comm == null ? "0" : comm.toString()));
                this.saveOrUpdateWeek(con);
            } catch (Exception e) {
                errorAgency++;
                LOGGER.error(String.format("经纪人[id=%1s]业绩汇总异常",id), e);
            }
        }
        LOGGER.info( String.format("经纪人按周统计业绩完成：总数%1s,失败%2s",agencyTotal,errorAgency ) );
    }

    /**
     * 按月统计
     * @param agencies
     * @throws Exception
     */
    @Override
    public void saveStatisticsMonth(List<Agency> agencies) throws Exception {
        this.deleteMonthAll();
        int errorAgency = 0;
        int agencyTotal = agencies.size();
        Map<String, Object> commission = this.queryCommissionMonth();
        Map<String, Object> reportMonth = this.queryReportMonth();
        Map<String, Object> visitMonth = this.queryVisitMonth();
        Map<String, Object> dealMonth = this.queryDealMonth();
        AgencyMonthContribution con ;
        for (Agency agency : agencies){
            String id = agency.getId().toString();
            con = new AgencyMonthContribution();
            try {
                Object reportNum = reportMonth.get(id);
                Object visitNum = visitMonth.get(id);
                Object dealNum = dealMonth.get(id);
                Object comm = commission.get(id);
                con.setAgencyId(agency.getId());
                con.setReportNum(Integer.valueOf(reportNum == null ? "0" : reportNum.toString()));
                con.setVisitNum(Integer.valueOf(visitNum == null ? "0" : visitNum.toString()));
                con.setDealNum(Integer.valueOf(dealNum == null ? "0" : dealNum.toString()));
                con.setCommission(Integer.valueOf(comm == null ? "0" : comm.toString()));
                this.saveOrUpdateMonth(con);
            } catch (Exception e) {
                errorAgency++;
                LOGGER.error(String.format("经纪人[id=%1s]业绩汇总异常",id), e);
            }
        }
        LOGGER.info( String.format("经纪人按月统计业绩完成：总数%1s,失败%2s",agencyTotal,errorAgency ) );
    }
    /**
     * 按所有统计
     * @param agencies
     * @throws Exception
     */
    @Override
    public void saveStatisticsAll(List<Agency> agencies) throws Exception {
        this.deleteMonthAll();
        int errorAgency = 0;
        int agencyTotal = agencies.size();
        Map<String, Object> commission = this.queryCommissionAll();
        Map<String, Object> reportAll = this.queryReportAll();
        Map<String, Object> visitAll = this.queryVisitAll();
        Map<String, Object> dealAll = this.queryDealAll();
        AgencyContribution con ;
        for (Agency agency : agencies){
            String id = agency.getId().toString();
            con = new AgencyContribution();
            try {
                Object reportNum = reportAll.get(id);
                Object visitNum = visitAll.get(id);
                Object dealNum = dealAll.get(id);
                Object comm = commission.get(id);
                con.setAgencyId(agency.getId());
                con.setReportNum(Integer.valueOf(reportNum == null ? "0" : reportNum.toString()));
                con.setVisitNum(Integer.valueOf(visitNum == null ? "0" : visitNum.toString()));
                con.setDealNum(Integer.valueOf(dealNum == null ? "0" : dealNum.toString()));
                con.setCommission(Integer.valueOf(comm == null ? "0" : comm.toString()));
                this.saveOrUpdate(con);
            } catch (Exception e) {
                errorAgency++;
                LOGGER.error(String.format("经纪人[id=%1s]业绩汇总异常",id), e);
            }
        }
        LOGGER.info( String.format("经纪人统计业绩完成：总数%1s,失败%2s",agencyTotal,errorAgency ) );
    }
}
