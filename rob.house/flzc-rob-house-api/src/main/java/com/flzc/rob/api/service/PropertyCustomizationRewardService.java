package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.PropertyCustomizationReward;

/**
 * 私人定制奖励信息
 * @author james
 *
 */
public interface PropertyCustomizationRewardService {

	/**
	 * 根据活动id查询定制活动奖项信息
	 * @param activityId
	 * @return
	 * @throws Exception
	 */
	public PropertyCustomizationReward queryPropertyCustomizationRewardByActivityId(Integer activityId) throws Exception;
}
