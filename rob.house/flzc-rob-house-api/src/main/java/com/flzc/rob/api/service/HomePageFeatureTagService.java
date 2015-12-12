package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.HouseBuildingTags;

/**
 * 
 * @author qj
 *
 */
public interface HomePageFeatureTagService {

    /**
     * 查询楼盘标签列表
     */
    public List<HouseBuildingTags> queryHouseBuildingTags() throws Exception;
}
