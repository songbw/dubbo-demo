package com.flzc.quartz.service.impl;

import com.flzc.base.util.Memcached;
import com.flzc.base.util.MemcachedKeyConstant;
import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.service.AgencyClientIntentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by iverson on 2015/12/9.
 */
@Service("agencyClientIntentService")
public class AgencyClientIntentServiceImpl implements AgencyClientIntentService {

    private static  final  int visited = 57004 ;
    private static final  int undealed = 57009 ;

    @Autowired
    private CommonDao commonDao;

    /**
     * 查询所有在带看后30天内没有成交的总数
     * 已带看：57004
     *
     * @return
     */
    @Override
    public int queryTimeoutIntentTotal() {
        String sql = "select count(id) from agency_client_intention_building b" +
                " where DATE_FORMAT(date_add(b.update_time, INTERVAL 30 DAY),'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d')  and  status=" + visited ;
        return  this.commonDao.findCountBySql(sql);
    }

    /**
     * 查询所有在带看后30天内没有成交的总数
     *
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Map<String,Object>> queryTimeoutIntent(int curPage, int pageSize) {
        String sql = "select id ,client_id as clientId , agency_id as agencyId from agency_client_intention_building b " +
                "where DATE_FORMAT(date_add(b.update_time, INTERVAL 30 DAY),'%Y%m%d') = DATE_FORMAT(now(),'%Y%m%d')  and  status=" + visited ;
        List<Map<String, Object>> rows= this.commonDao.findBySql(sql,(curPage-1)*pageSize ,pageSize);
        if(rows==null) return Collections.EMPTY_LIST;
        return rows;
    }

    /**
     * 将购房意向设置为未成交
     *
     * @param intentId
     */
    @Override
    public void updateDealFailure(int intentId) {
         String sql = "update agency_client_intention_building set status = %1s , update_time = now() where id= %2s ";
        this.commonDao.updateBySql(String.format(sql,undealed,intentId));
    }

    @Override
    public String queryAgency(int agencyId) {
        String key = MemcachedKeyConstant.AGENCY + "invite." + agencyId;
        String invite =  (String) Memcached.get(key);
        if(StringUtils.isNotBlank(invite)) return invite;
        String sql = "select invite_code as code from agency where id=" + agencyId;
        Map<String, Object> map = this.commonDao.findUniqueBySql(sql);
        if(map != null &&  !map.isEmpty()){
            Memcached.set(key,map.get("code").toString(),Calendar.MONTH,1);
            return map.get("code").toString();
        }
        return null;

    }
}
