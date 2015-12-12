package com.flzc.rob.api.service;

/**
 * 开发商额外打赏服务
 * Created by iverson on 2015/11/16.
 */
public interface HouseBuildingRewardService {
    /**
     * 根据开发商查询打赏金额
     * @param buildingId
     * @return
     */
    public Integer queryRewordByBuildingId(Integer  buildingId,Integer type);

}
