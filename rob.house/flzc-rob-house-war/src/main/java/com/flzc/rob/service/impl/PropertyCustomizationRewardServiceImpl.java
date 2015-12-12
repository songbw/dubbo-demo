package com.flzc.rob.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.PropertyCustomizationReward;
import com.flzc.rob.api.service.PropertyCustomizationRewardService;

@Service("propertyCustomizationRewardService")
public class PropertyCustomizationRewardServiceImpl implements
		PropertyCustomizationRewardService {

	private final static Logger logger = LoggerFactory.getLogger(PropertyCustomizationRewardServiceImpl.class);
	
	@Autowired
	private CommonDao commonDao;
	
	@Override
	public PropertyCustomizationReward queryPropertyCustomizationRewardByActivityId(
			Integer activityId) throws Exception {
		
		try {

			PropertyCustomizationReward reward = new PropertyCustomizationReward();
			reward.setActivityId(activityId);
			
			return commonDao.findUniqueObj(reward);
		} catch (Exception e) {
			logger.error("根据活动id查询定制活动奖项信息异常",e);
			throw e;
		}
	}

}
