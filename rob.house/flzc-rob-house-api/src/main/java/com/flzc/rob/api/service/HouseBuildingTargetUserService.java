package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.HouseBuildingTargetUser;

/**
 * 楼盘目标客户服务
 */
public interface HouseBuildingTargetUserService {
    /**
     * 查询楼盘目标客户
     * @param buildingId
     * @return
     */
    public HouseBuildingTargetUser queryByBuildingId(Integer buildingId) throws Exception;
}
