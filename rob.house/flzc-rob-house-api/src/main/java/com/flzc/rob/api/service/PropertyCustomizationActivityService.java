package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.PropertyCustomizationActivity;

/**
 * 私人定制活动接口
 * Created by iverson on 2015/10/22.
 */
public interface PropertyCustomizationActivityService {
    /**
     * 根据id查询活动信息
     * @param id
     * @return
     */
    public PropertyCustomizationActivity queryById(Integer id) throws Exception;
    
    /**
	 * 获取房链券使用规则
	 * @Title: queryTicketUseRule
	 * @param ticketId
	 * @param activityId
	 * @return
	 */
	public String queryPropertyTicketUseRule(Integer ticketId,Integer activityId);

}
