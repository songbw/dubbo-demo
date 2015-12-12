package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.HouseBuildingTagsRecLink;

public interface HouseBuildingTagsRecLinkService {
	
	/**
     * 根据楼盘id查询
     * @param buildingId
     * @return
     * @throws Exception
     */
    public List<HouseBuildingTagsRecLink> queryByBuildingId(Integer buildingId) throws Exception;
}
