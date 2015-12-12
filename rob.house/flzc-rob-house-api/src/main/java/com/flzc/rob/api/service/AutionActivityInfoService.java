package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.AutionActivityInfo;

/**
 * 竞拍活动详情服务
 * @ClassName: AutionActivityInfoService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午1:34:41
 */
public interface AutionActivityInfoService {

	/**
	 * 保存竞拍活动
	 * @Title: saveAutionActivityInfo 
	 * @Description: TODO
	 * @param info
	 * @return
	 * @return: boolean
	 */
	public Integer saveAutionActivityInfo(AutionActivityInfo info);
	
	/**
	 * 根据条件查询竞拍活动详情
	 * @Title: queryAutionActivityInfo 
	 * @Description: TODO
	 * @return
	 * @return: AutionActivityInfo
	 */
	public AutionActivityInfo queryAutionActivityInfo(AutionActivityInfo info);
	
	/**
	 * 根据楼盘id查询竞拍活动信息
	 * @Title: queryAutionActivityInfoByBuildingId 
	 * @Description: TODO
	 * @param buildingId
	 * @return
	 * @return: AutionActivityInfo
	 */
	public AutionActivityInfo queryAutionActivityInfoByBuildingId(Integer buildingId);
	
	/**
	 * 根据id查询竞拍活动信息
	 * @Title: queryAutionActivityInfoById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: AutionActivityInfo
	 */
	public AutionActivityInfo queryAutionActivityInfoById(Integer id);
	
	/**
	 * 获取房链券使用规则
	 * @Title: queryTicketUseRule
	 * @param ticketId
	 * @param activityId
	 * @return
	 */
	public String queryAuctionTicketUseRule(Integer ticketId,Integer activityId);
}
