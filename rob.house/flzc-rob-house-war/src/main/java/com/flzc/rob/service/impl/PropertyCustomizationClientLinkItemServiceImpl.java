package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.PropertyCustomizationClientLinkItem;
import com.flzc.rob.api.service.PropertyCustomizationClientLinkItemService;

@Service("propertyCustomizationClientLinkItemService")
public class PropertyCustomizationClientLinkItemServiceImpl extends BaseServiceImpl implements PropertyCustomizationClientLinkItemService{

	private final static Logger logger = LoggerFactory.getLogger(PropertyCustomizationClientLinkItemServiceImpl.class);
	
	@Override
	public boolean savePropertyCustomizationClientLinkItem(PropertyCustomizationClientLinkItem item) {

		try{
			
			this.save(item);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("保存客户定制失败，客户id为：" + item.getUserId() + "，活动id为：" + item.getActivityId(), e);
			throw e;
		}
	}

	@Override
	public List<PropertyCustomizationClientLinkItem> queryPropertyCustomizationClientLinkItemByActivityId(
			Integer activityId) {

		String hql = "from PropertyCustomizationClientLinkItem where activityId=:activityId and recordId = 0";
		List<PropertyCustomizationClientLinkItem> list = this.findByNamedParam(
				hql, "activityId", activityId);
		return list;
	}

	@Override
	public PropertyCustomizationClientLinkItem queryPropertyCustomizationClientLinkItemById(Integer id) {

		return this.findById(PropertyCustomizationClientLinkItem.class, id);
	}


	@Override
	public List<Integer> queryGroupParentIdByRecordId(Integer recordId) {

		String hql = "select parentId from PropertyCustomizationClientLinkItem "
				+ "where recordId=:recordId group by parentId";
		
		List<Integer> list = this.findByNamedParam(hql, "recordId", recordId);
		return list;
	}

	@Override
	public PropertyCustomizationClientLinkItem queryPccliByRecordIdAndItemId(Integer recordId, Integer itemId) {

		String hql = "from PropertyCustomizationClientLinkItem where recordId=:recordId and itemId=:itemId";
		List<PropertyCustomizationClientLinkItem> list = this.findByNamedParam(
				hql, new String[]{"recordId","itemId"}, new Object[]{recordId,itemId});
		
		return (list==null || list.isEmpty())?null:list.get(0);
	}

	@Override
	public List<PropertyCustomizationClientLinkItem> queryPccliByRecordId(Integer recordId) {

		String hql = "from PropertyCustomizationClientLinkItem where recordId=:recordId";
		List<PropertyCustomizationClientLinkItem> list = this.findByNamedParam(hql, "recordId", recordId);
		return list;
	}

	@Override
	public Integer queryCustomCountByItemIdAndActivityId(Integer itemId, Integer activityId) {

		String hql = "select count(id) from PropertyCustomizationClientLinkItem "
				+ "where itemId=:itemId and activityId=:activityId";
		
		List<Long> list = this.findByNamedParam(hql, new String[]{"itemId","activityId"}, new Object[]{itemId,activityId});
		return (list==null||list.isEmpty())?0:Integer.valueOf(list.get(0).toString());
	}

}
