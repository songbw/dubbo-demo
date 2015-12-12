package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.rob.api.entity.HouseListFeatureTag;

/**
 * 线上房展
 * @ClassName: HouseListFeatureTagService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年12月2日 下午4:44:22
 */
public interface HouseListFeatureTagService {

	/**
	 * 查询线上房展
	 * @Title: queryHouseListFeatureTagList 
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: List<HouseListFeatureTag>
	 */
	public List<HouseBuildingTags> queryHouseListFeatureTagList(
			Integer page,Integer pageSize);
}
