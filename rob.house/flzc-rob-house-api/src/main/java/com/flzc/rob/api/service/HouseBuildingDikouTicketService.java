package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingDikouTicket;

/**
 * 红包抵扣服务
 */
public interface HouseBuildingDikouTicketService {
    /**
     * 根据楼盘查询抵扣
     * @param buildingId
     * @return
     */
    public HouseBuildingDikouTicket queryByBuildingId(Integer buildingId) throws Exception;
    
    /**
     * 更新红包抵扣可售数
     * @param ticket
     * @throws Exception
     */
    public void updateDikouActivityInfo(HouseBuildingDikouTicket ticket) throws Exception;
}
