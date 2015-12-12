
package com.flzc.rob.api.service;

import java.util.List;
import java.util.Map;

import com.flzc.rob.api.entity.HouseBuildingView;

/**
 * 楼盘浏览服务类
  * @ClassName: HouseBuildingViewService
  * @Description: TODO
  * @author qj
  * @date 2015-10-30 下午2:23:59
  *
 */
public interface HouseBuildingViewService {

	/**
	 * 保存楼盘浏览
	 * @Title: saveHouseBuildingView
	 * @param HouseBuildingView
	 * @return
	 * @throws Exception
	 */
	public Integer saveHouseBuildingView(HouseBuildingView houseBuildingView) throws Exception;
	
	/**
	 * 查询我的浏览列表
	 * @Title: queryHouseBuildingViewList
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<HouseBuildingView> queryUserHouseBuildingViewList(Integer userId) throws Exception;
	
	/**
	 * 删除我浏览的楼盘
	 * @Title: deleteUserHouseBuildingView
	 * @param houseBuildingView
	 * @throws Exception
	 */
	public void deleteUserHouseBuildingView(HouseBuildingView houseBuildingView) throws Exception;
	
	/**
	 * 根据主键id查询浏览楼盘
	 * @Title: queryUserHouseBuildingViewById
	 * @param viewId
	 * @return
	 * @throws Exception
	 */
	public HouseBuildingView queryUserHouseBuildingViewById(Integer viewId) throws Exception;
	
	/**
	 * 查询浏览楼盘
	 * @Title: queryUserHouseBuildingViewById
	 * @param houseBuildingView
	 * @return
	 * @throws Exception
	 */
	public HouseBuildingView queryUserHouseBuildingView(HouseBuildingView houseBuildingView) throws Exception;
	
	/**
	 * 查询用户浏览楼盘
	 * @Title: queryViewBuildingList
	 * @param param
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> queryViewBuildingList(Map<String,Object> param , int curPage , int pageSize);
}
