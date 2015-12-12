
package com.flzc.rob.api.service;

import java.util.List;
import java.util.Map;

import com.flzc.rob.api.entity.HouseBuildingCollect;

/**
 * 楼盘收藏
  * @ClassName: HouseBuildingCollectService
  * @Description: TODO
  * @author qj
  * @date 2015-10-30 下午2:23:59
  *
 */
public interface HouseBuildingCollectService {

	/**
	 * 保存楼盘收藏
	 * @Title: saveHouseBuildingShare
	 * @param houseBuildingCollect
	 * @return
	 * @throws Exception
	 */
	public Integer saveHouseBuildingCollect(HouseBuildingCollect houseBuildingCollect) throws Exception;
	
	/**
	 * 查询我的收藏列表
	 * @Title: queryUserHouseBuildingCollectList
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<HouseBuildingCollect> queryUserHouseBuildingCollectList(Integer userId,
			Integer page,Integer pageSize) throws Exception;
	
	/**
	 * 查询我的收藏列数量
	 * @Title: queryUserHouseBuildingCollectList
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int queryUserHouseBuildingCollectListSize(Integer userId) throws Exception;
	
	/**
	 * 删除我的楼盘收藏
	 * @Title: deleteUserHouseBuildingCollectById
	 * @param houseBuildingCollect
	 * @throws Exception
	 */
	public void deleteUserHouseBuildingCollect(HouseBuildingCollect houseBuildingCollect) throws Exception;
	
	/**
	 * 根据主键id查询楼盘收藏
	 * @Title: deleteUserHouseBuildingCollectById
	 * @param collectId
	 * @throws Exception
	 */
	public HouseBuildingCollect queryUserHouseBuildingCollectById(Integer collectId) throws Exception;
	
	/**
	 * 根据楼盘收藏
	 * @Title: queryUserHouseBuildingCollect
	 * @param houseBuildingCollect
	 * @throws Exception
	 */
	public HouseBuildingCollect queryUserHouseBuildingCollect(HouseBuildingCollect houseBuildingCollect) throws Exception;

	/**
	 * 查询是否收藏
	 * @Title: queryHouseBuildingCollectByUserIdAndActIdAndBuildingId 
	 * @Description: TODO
	 * @param userId
	 * @param actId
	 * @param buildingId
	 * @return
	 * @return: HouseBuildingCollect
	 */
	public HouseBuildingCollect queryHouseBuildingCollectByUserIdAndBuildingId(
			Integer userId,Integer buildingId);

	
	/**
	 * 查询用户收藏楼盘
	 * @Title: queryCollectBuildingList
	 * @param param
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> queryCollectBuildingList(Map<String,Object> param , int curPage , int pageSize);
}
