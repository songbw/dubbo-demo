package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.entity.Agency;
import com.flzc.quartz.entity.AgencyTopsMonth;
import com.flzc.quartz.service.AgencyTopsMonthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 经纪人排行榜服务接口，统计最近30天
 */
@Service("agencyTopsMonthService")
public class AgencyTopsMonthServiceImpl implements AgencyTopsMonthService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(AgencyTopsMonthServiceImpl.class );
    @Autowired
    private CommonDao commonDao;

    /**
     * 把所有数据置为无效
     */
    @Override
    public void updateAllInvalid() {
        String sql = "update  agency_tops_month a set a.status=1 , update_time=now() where a.status=0 ";
        this.commonDao.updateBySql(sql);
    }

    @Override
    public void save(AgencyTopsMonth record ) throws Exception {
        record.setStatus(0);
        record.setCreateTime(new Date());
        this.commonDao.save(record);
    }

    /**
     * 查询经纪人总数
     *
     * @return
     */
    @Override
    public int queryAgencyTotalNum() {
         String sql = "select count(id)  from agency where status=0";
         return  this.commonDao.findCountBySql(sql);
    }

    /**
     * 分页查询经纪人
     *
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Agency> queryAgencies(int curPage, int pageSize) throws Exception {
        Agency param = new Agency();
        param.setStatus(0);
        if(curPage==0) curPage = 1;
        if(pageSize==0) pageSize = 50 ;
        return  this.commonDao.findObjsWithPage(param, curPage, pageSize);
    }

    /**
     * 报备
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryReport() {
        String sql ="SELECT " +
                " c.agency_id AS agencyId, " +
                " COUNT(a.id) AS total " +
                "FROM " +
                " agency_client_intention_building a " +
                "LEFT JOIN agency_client c ON a.client_id = c.id " +
                "WHERE " +
                " a.create_time >= ADDDATE( " +
                "  DATE_FORMAT(a.create_time, '%Y-%m-%d'), " +
                "  -30 " +
                " ) " +
                "AND a. STATUS in ( 57003 ,57004,57005,57006,57009,57010 )" +
                "GROUP BY " +
                " c.agency_id " +
                "ORDER BY " +
                " c.agency_id ";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.emptyList();
        return  rows;
    }

    /**
     * 带看
     * @return
     */
    @Override
    public List<Map<String, Object>> queryVisit() {
        String sql ="SELECT " +
                " c.agency_id AS agencyId, " +
                " COUNT(a.id) AS total " +
                "FROM " +
                " agency_client_intention_building a " +
                "LEFT JOIN agency_client c ON a.client_id = c.id " +
                "WHERE " +
                " a.create_time >= ADDDATE( " +
                "  DATE_FORMAT(a.create_time, '%Y-%m-%d'), " +
                "  -30 " +
                " ) " +
                "AND a. STATUS in ( 57004,57005,57006,57009,57010 ) " + // 已带看,成交,已结佣,未成交,待结佣
                "GROUP BY " +
                " c.agency_id " +
                "ORDER BY " +
                " c.agency_id ";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.emptyList();
        return  rows;
    }

    /**
     * 成交
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDeal() {
        String sql ="SELECT " +
                " c.agency_id AS agencyId, " +
                " COUNT(a.id) AS total " +
                "FROM " +
                " agency_client_intention_building a " +
                "LEFT JOIN agency_client c ON a.client_id = c.id " +
                "WHERE " +
                " a.create_time >= ADDDATE( " +
                "  DATE_FORMAT(a.create_time, '%Y-%m-%d'), " +
                "  -30 " +
                " ) " +
                "AND a. STATUS in( 57005,57006,57010 )" + //已带看,已结佣,57010
                "GROUP BY " +
                " c.agency_id " +
                "ORDER BY " +
                " c.agency_id ";

        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.emptyList();
        return  rows;
    }

    /**
     * 客户数
     *
     * @param agencyId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryClient(List<Integer> agencyId) {
        return null;
    }

    /**
     * 查询最近30天的佣金排行
     * @return
     */
    @Override
    public List<Map<String,Object>> queryCommission(){
        String sql = "SELECT " +
                " agency_id AS agencyId, " +
                " sum(amount) AS amount " +
                "FROM " +
                " `agency_commission` " +
                "WHERE " +
                "STATUS = 1 " +
                "AND DATE_FORMAT(create_time, '%Y-%m-%d') >= ADDDATE( " +
                " DATE_FORMAT(now(), '%Y-%m-%d') ,- 30 " +
                ") " +
                "GROUP BY " +
                " agency_id " +
                "ORDER BY " +
                " agency_id";
        List<Map<String, Object>> rows = this.commonDao.findBySql(sql);
        if(rows==null) return Collections.EMPTY_LIST;
        return rows;
    }
}
