package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.base.service.BaseService;
import com.flzc.rob.api.entity.PropertyCustomizationLinkItem;

/**
 * 定制活动选项关联服务接口
 * @ClassName: PropertyCustomizationLinkItemService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午4:39:59
 */
public interface PropertyCustomizationLinkItemService extends BaseService{

	/**
	 * 根据活动id分组查询定制分类父id
	 * @Title: queryPropertyCustomizationLinkItemByParentIdAndActivityId 
	 * @Description: TODO
	 * @param parentId
	 * @param activityId
	 * @return
	 * @return: List<Integer>
	 */
	public List<Integer> queryPropertyCustomizationLinkItemParentIdByActivityId(
			Integer activityId);
	
	/**
	 * 根据父id和活动id查询子选项
	 * @Title: queryPropertyCustomizationLinkItemByActivityIdAndParentId 
	 * @Description: TODO
	 * @param activityId
	 * @param parentId
	 * @return
	 * @return: List<PropertyCustomizationLinkItem>
	 */
	public List<PropertyCustomizationLinkItem> queryPropertyCustomizationLinkItemByActivityIdAndParentId(
			Integer activityId,Integer parentId);
	
}
