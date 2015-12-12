package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.PropertyCustomizationFollow;
import com.flzc.rob.api.service.PropertyCustomizationFollowService;

@Service("propertyCustomizationFollowService")
public class PropertyCustomizationFollowServiceImpl extends BaseServiceImpl implements PropertyCustomizationFollowService{

	private final static Logger logger = LoggerFactory.getLogger(PropertyCustomizationFollowServiceImpl.class);
	
	@Override
	public boolean savePropertyCustomizationFollow(PropertyCustomizationFollow follow) {

		try{
			
			this.save(follow);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存定制关注失败，用户id为：" + follow.getUserId() + ",记录id为：" + follow.getRecordId(), e);
			throw e;
		}
	}

	@Override
	public Integer queryPropertyCustomizationFollowCountByRecordId(Integer recordId) {

		String hql = "select count(id) from PropertyCustomizationFollow where status = 0 and recordId=:recordId";
		List<Long> list = this.findByNamedParam(hql, "recordId", recordId);
		return (list==null||list.isEmpty())?0:Integer.valueOf(list.get(0).toString());
	}

}
