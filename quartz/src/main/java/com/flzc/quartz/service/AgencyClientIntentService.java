package com.flzc.quartz.service;

import java.util.List;
import java.util.Map;

/**
 * 客户购房意向服务类
 */
public interface AgencyClientIntentService {
    /**
     * 查询所有在带看后30天内没有成交的总数
     * 已带看：57004
     * @return
     */
    int queryTimeoutIntentTotal();

    /**
     *  查询所有在带看后30天内没有成交的总数
     * @param curPage
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> queryTimeoutIntent(int curPage ,int pageSize);

    /**
     * 将购房意向设置为未成交
     * @param intentId
     */
    void updateDealFailure(int intentId);

    public String queryAgency(int agencyId);

}
