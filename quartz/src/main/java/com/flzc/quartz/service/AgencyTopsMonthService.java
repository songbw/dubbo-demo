package com.flzc.quartz.service;

import com.flzc.quartz.entity.Agency;
import com.flzc.quartz.entity.AgencyTopsMonth;

import java.util.List;
import java.util.Map;

/**
 * 经纪人排行榜服务接口，统计最近30天
 */
public interface AgencyTopsMonthService {

    /**
     * 把所有数据置为无效
     */
    public  void updateAllInvalid();

    public void save(AgencyTopsMonth record) throws Exception;


    /**
     * 查询经纪人总数
     * @return
     */
    public int queryAgencyTotalNum();

    /**
     * 分页查询经纪人
     * @param curPage
     * @param pageSize
     * @return
     */
    public List<Agency> queryAgencies(int curPage , int pageSize) throws Exception;

    /**
     * 报备
     * @return
     */
    public List<Map<String,Object>> queryReport();

    /**
     * 带看
     * @param agencyId
     * @return
     */
    public List<Map<String,Object>> queryVisit();

    /**
     * 成交
     * @param agencyId
     * @return
     */
    public List<Map<String,Object>> queryDeal();

    /**
     * 客户数
     * @param agencyId
     * @return
     */
    public List<Map<String,Object>> queryClient(List<Integer> agencyId);


    List<Map<String,Object>> queryCommission();
}
