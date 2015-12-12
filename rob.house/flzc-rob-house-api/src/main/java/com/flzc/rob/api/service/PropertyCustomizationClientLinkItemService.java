package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.PropertyCustomizationClientLinkItem;

/**
 * 私人定制客户订制关联项服务接口
 * @ClassName: PropertyCustomizationClientLinkItemService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午3:45:25
 */
public interface PropertyCustomizationClientLinkItemService {

	/**
	 * 保存私人定制客户订制关联项
	 * @Title: savePropertyCustomizationClientLinkItem 
	 * @Description: TODO
	 * @param item
	 * @return
	 * @return: boolean
	 */
	public boolean savePropertyCustomizationClientLinkItem(
			PropertyCustomizationClientLinkItem item);
	
	/**
	 * 根据活动id查询用户定制记录、记录id为0的
	 * @Title: queryPropertyCustomizationClientLinkItemByActivityId 
	 * @Description: TODO
	 * @param activityId
	 * @return
	 * @return: List<PropertyCustomizationClientLinkItem>
	 */
	public List<PropertyCustomizationClientLinkItem> queryPropertyCustomizationClientLinkItemByActivityId(Integer activityId);

	/**
	 * 根据recordid查询定制信息
	 * @Title: queryPropertyCustomizationClientLinkItemById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: PropertyCustomizationClientLinkItem
	 */
	public PropertyCustomizationClientLinkItem queryPropertyCustomizationClientLinkItemById(Integer id);
	
	/**
	 * 根据id查询父分类信息
	 * @Title: queryGroupParentIdByPid 
	 * @Description: TODO
	 * @param pid
	 * @return
	 * @return: List<Integer>
	 */
	public List<Integer> queryGroupParentIdByRecordId(Integer recordId);
	
	/**
	 * 根据基础信息id和选项id查询用户选项信息
	 * @Title: queryPccliByRecordIdAndItemId 
	 * @Description: TODO
	 * @param recordId
	 * @param itemId
	 * @return
	 * @return: PropertyCustomizationClientLinkItem
	 */
	public PropertyCustomizationClientLinkItem queryPccliByRecordIdAndItemId(Integer recordId,Integer itemId);

	/**
	 * 根据recordId查询用户选项列表
	 */
	public List<PropertyCustomizationClientLinkItem> queryPccliByRecordId(Integer recordId);
	
	/**
	 * 根据选项id和活动id查询选项数量
	 */
	public Integer queryCustomCountByItemIdAndActivityId(Integer itemId,Integer activityId);
}
