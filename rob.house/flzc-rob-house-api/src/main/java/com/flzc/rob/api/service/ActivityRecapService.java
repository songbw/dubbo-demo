package com.flzc.rob.api.service;

import java.util.List;
import java.util.Map;

import com.flzc.rob.api.entity.ActivityRecap;

/**
 * 活动汇集业务接口
 * @ClassName: ActivityRecapService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午9:45:05
 */
public interface ActivityRecapService {

	/**
	 * 保存活动
	 * @Title: saveActivityRecap 
	 * @Description: TODO
	 * @param activityRecap
	 * @return
	 * @return: Integer
	 */
	public Integer saveActivityRecap(ActivityRecap activityRecap);
	
	/**
	 * 根据楼盘id查询楼盘汇总
	 * @Title: queryActivityRecapByBuildingId 
	 * @Description: TODO
	 * @param buildingId
	 * @return
	 * @return: List<ActivityRecap>
	 */
	public List<ActivityRecap> queryActivityRecapByBuildingId(Integer buildingId);
	
	/**
	 * 根据楼盘ID、活动类型查询汇总
	 * @param building
	 * @param type
	 * @return
	 */
	public ActivityRecap queryActivityRecapByBuildingAndType(Integer buildingId, Integer type);
	
	
	
	/**
	 * 查询即将上线活动数
	 * @return
	 */
	public Integer queryAboutOnLineActivityCount(Integer cityId);
	
	/**
	 * 根据id查询活动汇总
	 * @Title: queryActivityRecapById
	 * @param activityRecapId
	 * @return
	 * @throws Exception 
	 */
	public ActivityRecap queryActivityRecapById(Integer activityRecapId) throws Exception;
	
	
	/**
	 * 根据活动id和活动类型的id查询活动汇总信息
	 * @param activityId
	 * @param activityTypeId
	 * @return
	 * @throws Exception
	 */
	public ActivityRecap queryActivityRecapById(Integer activityId,Integer activityTypeId) throws Exception;

	public List<ActivityRecap> queryActivityListByCityAndPage(Integer cityId,Integer page, Integer pageSize);

	public List<ActivityRecap> queryActivityRecapByBId(Integer buildingId);
}
