package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.HouseBuildingDynamic;

/**
 * 楼盘动态信息接口
 * @author bing.xiao
 *
 */
public interface HouseBuildingDynamicService {

	/**
	 * 根据楼盘ID查询楼盘动态
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public List<HouseBuildingDynamic> queryByBuildingId(Integer buildingId, Integer page, Integer pageSize) throws Exception;
	
	/**
	 * 根据楼盘ID查询楼盘动态总数
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public Integer queryCountByBuildingId(Integer buildingId) throws Exception;
}
