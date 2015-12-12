package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.ActivityRule;

/**
 * 竞拍活动协议服务
 * @ClassName: AutionActivityRuleService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午1:39:41
 */
public interface ActivityRuleService {

	/**
	 * 根据条件查询协议
	 * @Title: queryAutionActivityRule 
	 * @Description: TODO
	 * @param rule
	 * @return
	 * @return: AutionActivityRule
	 */
	public ActivityRule queryActivityRule(ActivityRule rule);
	
	/**
	 * 根据id查询协议
	 * @Title: queryActivityRuleById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: ActivityRule
	 */
	public ActivityRule queryActivityRuleById(Integer id);
}
