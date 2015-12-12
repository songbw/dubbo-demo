package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.PropertyCustomizationBaseInfo;

/**
 * 参与活动基本信息表
 * @ClassName: PropertyCustomizationBaseInfoService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午9:44:56
 */
public interface PropertyCustomizationBaseInfoService {

	/**
	 * 保存用户定制活动基本信息
	 * @Title: savePropertyCustomizationBaseInfo 
	 * @Description: TODO
	 * @param info
	 * @return
	 * @return: boolean
	 */
	public PropertyCustomizationBaseInfo savePropertyCustomizationBaseInfo(PropertyCustomizationBaseInfo info);
	
	/**
	 * 根据id查询基本信息
	 * @Title: queryPropertyCustomizationBaseInfoById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: PropertyCustomizationBaseInfo
	 */
	public PropertyCustomizationBaseInfo queryPropertyCustomizationBaseInfoById(Integer id);


	/**
	 * 根据活动id查询活动定制基本信息
	 * @Title: queryPcBiListByActivityIdAndPid 
	 * @Description: TODO
	 * @param activityId
	 * @return
	 * @return: List<PropertyCustomizationBaseInfo>
	 */
	public List<PropertyCustomizationBaseInfo> queryPcBiListByActivityIdAndPid(Integer activityId, Integer page,Integer pageSize);
	
	/**
	 * 根据活动id查询活动定制基本信息
	 * @Title: queryPcBiListByActivityIdAndPid 
	 * @Description: TODO
	 * @param activityId
	 * @return
	 * @return: List<PropertyCustomizationBaseInfo>
	 */
	public List<PropertyCustomizationBaseInfo> queryPcBiListByActivityIdAndPid(Integer activityId);

	/**
	 * 根据pid查询我想要count
	 * @Title: queryPcBiCountByPid 
	 * @Description: TODO
	 * @param pid
	 * @return
	 * @return: Integer
	 */
	public Integer queryPcBiCountByPid(Integer pid);
	
	/**
	 * 根据活动id查询用户定制数
	 * @Title: queryPcBiCountByActivityId 
	 * @Description: TODO
	 * @param activityId
	 * @return
	 * @return: Integer
	 */
	public Integer queryPcBiCountByActivityId(Integer activityId);
	
	/**
	 * 根据活动id分页查询用户定制
	 * @Title: queryPcbiListByActivityIdAndPage 
	 * @Description: TODO
	 * @param activityId
	 * @param page
	 * @return
	 * @return: List<PropertyCustomizationBaseInfo>
	 */
	public List<PropertyCustomizationBaseInfo> queryPcbiListByActivityIdAndPage(
			Integer activityId,Integer page,Integer pageSize);
	
	/**
	 * 根据活动id查询中奖记录
	 * @Title: queryRewardUserCount 
	 * @Description: TODO
	 * @param activityId
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: List<PropertyCustomizationBaseInfo>
	 */
	public List<PropertyCustomizationBaseInfo> queryRewardUserListByActId(
			Integer activityId,Integer page,Integer pageSize);
	
	/**
	 * 根据活动id查询中奖用户数
	 * @Title: queryRewardUserCountByActId 
	 * @Description: TODO
	 * @param activityId
	 * @return
	 * @return: Integer
	 */
	public Integer queryRewardUserCountByActId(
			Integer activityId);
}
