package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.PropertyCustomizationAssurance;
import com.flzc.rob.api.service.PropertyCustomizationAssuranceService;

@Service("propertyCustomizationAssuranceService")
public class PropertyCustomizationAssuranceServiceImpl extends BaseServiceImpl 
	implements PropertyCustomizationAssuranceService{

	private final static Logger logger = LoggerFactory.getLogger(PropertyCustomizationAssuranceServiceImpl.class);
	
	@Override
	public boolean savePropertyCustomizationAssurance(PropertyCustomizationAssurance pca) {

		try{
			
			this.save(pca);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存私人定制保障接口失败，活动id：" + pca.getActivityId(), e);
			throw e;
		}
	}

	@Override
	public PropertyCustomizationAssurance queryPropertyCustomizationAssuranceById(Integer id) {
		
		return this.findById(PropertyCustomizationAssurance.class, id);
	}

	@Override
	public PropertyCustomizationAssurance queryPropertyCustomizationAssuranceByActivityId(Integer activityId) {

		String hql = "from PropertyCustomizationAssurance where status = 0 and activityId=:activityId";
		List<PropertyCustomizationAssurance> list = this.findByNamedParam(hql, "activityId", activityId);
		
		return (list == null || list.isEmpty())?null:list.get(0);
	}

}
