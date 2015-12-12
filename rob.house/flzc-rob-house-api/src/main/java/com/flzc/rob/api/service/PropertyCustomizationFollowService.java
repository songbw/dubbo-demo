package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.PropertyCustomizationFollow;

/**
 * 定制关注服务接口实现
 * @ClassName: PropertyCustomizationFollowService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午4:31:17
 */
public interface PropertyCustomizationFollowService {

	/**
	 * 保存定制关注信息
	 * @Title: savePropertyCustomizationFollow 
	 * @Description: TODO
	 * @param follow
	 * @return
	 * @return: boolean
	 */
	public boolean savePropertyCustomizationFollow(PropertyCustomizationFollow follow);

	/**
	 * 根据记录id查询出收藏数
	 * @Title: queryPropertyCustomizationFollowCountByRecordId 
	 * @Description: TODO
	 * @param recordId
	 * @return
	 * @return: Integer
	 */
	public Integer queryPropertyCustomizationFollowCountByRecordId(Integer recordId);
}
