package com.flzc.rob.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.base.util.Constants;
import com.flzc.base.util.Memcached;
import com.flzc.base.util.MemcachedKeyConstant;
import com.flzc.rob.api.entity.ActivityRule;
import com.flzc.rob.api.service.ActivityRuleService;

@Service("activityRuleService")
public class ActivityRuleServiceImpl extends BaseServiceImpl implements ActivityRuleService{

	private final static Logger logger = LoggerFactory.getLogger(ActivityRuleServiceImpl.class);
	
	@Override
	public ActivityRule queryActivityRule(ActivityRule rule) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityRule queryActivityRuleById(Integer id) {

		ActivityRule rule = null;
		String key = MemcachedKeyConstant.BUILDING_ACTIVITY_RULE + id;
		try {

			rule = (ActivityRule) Memcached.get(key);
			if (rule != null)
				return rule;

			rule = this.findById(ActivityRule.class, id);

			Memcached.set(key, rule, Constants.CACHE_TIME,
							Constants.USER_INFO_USERID_TIME);
		} catch (Exception e) {
			logger.error("根据id查询协议异常", e);
			throw e;
		}
		return rule;
	}
}
