package com.flzc.rob.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.PropertyCustomizationLinkItem;
import com.flzc.rob.api.service.PropertyCustomizationLinkItemService;

@Service("propertyCustomizationLinkItemService")
public class PropertyCustomizationLinkItemServiceImpl extends BaseServiceImpl implements PropertyCustomizationLinkItemService{

	@Override
	public List<Integer> queryPropertyCustomizationLinkItemParentIdByActivityId(Integer activityId) {

		String hql = "select parentId from PropertyCustomizationLinkItem where activityId=:activityId group by parentId";
		List<Integer> list = this.findByNamedParam(hql, "activityId", activityId);
		return list;
	}

	@Override
	public List<PropertyCustomizationLinkItem> queryPropertyCustomizationLinkItemByActivityIdAndParentId(
			Integer activityId, Integer parentId) {

		String hql = "from PropertyCustomizationLinkItem where activityId=:activityId and parentId=:parentId";
		List<PropertyCustomizationLinkItem> list = this.findByNamedParam(
				hql, new String[]{"activityId","parentId"}, new Object[]{activityId,parentId});
		
		return list;
	}

}
