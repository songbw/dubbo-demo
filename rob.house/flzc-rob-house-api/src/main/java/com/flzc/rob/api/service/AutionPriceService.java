package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.AutionPrice;

/**
 * 用户参与竞拍活动服务
 * @ClassName: AutionPriceService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午1:44:37
 */
public interface AutionPriceService {

	/**
	 * 根据活动id条件查询竞拍详情列表信息
	 * @Title: queryAutionPriceByActivityId 
	 * @Description: TODO
	 * @param activityId
	 * @return
	 * @return: List<AutionPrice>
	 */
	public List<AutionPrice> queryAutionPriceByActivityId(Integer activityId);
	
	/**
	 * 保存用户竞拍信息
	 * @Title: saveAutionPrice 
	 * @Description: TODO
	 * @param aution
	 * @return
	 * @return: boolean
	 */
	public Integer saveAutionPrice(AutionPrice aution);
	
	/**
	 * 根据id查询用户竞拍信息
	 * @Title: queryAutionPriceById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: AutionPrice
	 */
	public AutionPrice queryAutionPriceById(Integer id);
	
	/**
	 * 根据活动id查询参与活动总人数
	 * @Title: queryAutionPriceCountByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: Integer
	 */
	public Integer queryAutionPriceCountByActId(Integer actId);
	
	/**
	 * 根据活动id分页查询活动人数
	 * @Title: queryAutionPriceByActIdAndPage 
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param actId
	 * @return
	 * @return: List<AutionPrice>
	 */
	public List<AutionPrice> queryAutionPriceByActIdAndPage(Integer page,Integer pageSize,Integer actId);

	/**
	 * 根据用户id和活动id查询用户出价列表
	 * @Title: queryAutionPriceByUserIdAndActId 
	 * @Description: TODO
	 * @param userId
	 * @param activityId
	 * @return
	 * @return: List<AutionPrice>
	 */
	public List<AutionPrice> queryAutionPriceByUserIdAndActId(Integer userId,Integer activityId);
	
	/**
	 * 根据活动id查询中奖用户
	 * @Title: queryRewardUser 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: AutionPrice
	 */
	public AutionPrice queryRewardUserByActId(Integer actId);
}
