
package com.flzc.rob.api.service;

import java.util.List;
import java.util.Map;

import com.flzc.rob.api.entity.HouseBuildingShare;

/**
 * 楼盘分享
  * @ClassName: HouseBuildingShareService
  * @Description: TODO
  * @author qj
  * @date 2015-10-30 下午2:23:59
  *
 */
public interface HouseBuildingShareService {

	/**
	 * 保存楼盘分享
	 * @Title: saveHouseBuildingShare
	 * @param houseBuildingShare
	 * @return
	 */
	public Integer saveHouseBuildingShare(HouseBuildingShare houseBuildingShare) throws Exception;
	
	/**
	 * 查询我的分享列表
	 * @Title: queryUserHouseBuildingShareList
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<HouseBuildingShare> queryUserHouseBuildingShareList(Integer userId) throws Exception;
	
	/**
	 * 删除我的楼盘分享
	 * @Title: deleteUserHouseBuildingShare
	 * @param houseBuildingShare
	 * @return
	 * @throws Exception
	 */
	public void deleteUserHouseBuildingShare(HouseBuildingShare houseBuildingShare) throws Exception;
	
	/**
	 * 根据主键id查询楼盘分享
	 * @Title: queryUserHouseBuildingShareById
	 * @param shareId
	 * @throws Exception
	 */
	public HouseBuildingShare queryUserHouseBuildingShareById(Integer shareId) throws Exception;
	
	/**
	 * 根据主键id查询楼盘分享
	 * @Title: queryUserHouseBuildingShareById
	 * @param houseBuildingShare
	 * @throws Exception
	 */
	public HouseBuildingShare queryUserHouseBuildingShare(HouseBuildingShare houseBuildingShare) throws Exception;
	
	/**
	 * 查询用户当天收藏次数
	 * @Title: queryUserShareTimes
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer queryUserShareTimes(Integer userId) throws Exception;
	
	/**
	 * 查询用户分享楼盘
	 * @Title: queryShareBuildingList
	 * @param param
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> queryShareBuildingList(Map<String,Object> param , int curPage , int pageSize);
}
