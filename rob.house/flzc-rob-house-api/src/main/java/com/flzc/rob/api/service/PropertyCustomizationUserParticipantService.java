package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.PropertyCustomizationUserParticipant;

/**
 * 用户定制活动参与服务
 * @ClassName: PropertyCustomizationUserParticipantService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午3:03:30
 */
public interface PropertyCustomizationUserParticipantService {

	/**
	 * 保存用户定制活动参与信息
	 * @Title: saveQuestionParticipantReward 
	 * @Description: TODO
	 * @param participant
	 * @return
	 * @return: Integer
	 * @throws Exception 
	 */
	public Integer savePropertyCustomizationUserParticipant(PropertyCustomizationUserParticipant participant) throws Exception;
	
	/**
	 * 根据id查询用户定制活动参与
	 * @Title: queryPropertyCustomizationUserParticipantById
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public PropertyCustomizationUserParticipant queryPropertyCustomizationUserParticipantById(Integer id) throws Exception;
	
	/**
	 * 根据条件查询用户定制活动信息
	 * @Title: queryPropertyCustomizationUserParticipant
	 * @param participant
	 * @return
	 * @throws Exception 
	 */
	public List<PropertyCustomizationUserParticipant> queryPropertyCustomizationUserParticipantList(PropertyCustomizationUserParticipant participant) throws Exception;
	
	/**
	 * 根据活动id查询参与人数
	 * @param actId
	 * @return
	 * @throws Exception
	 */
	public Integer queryCountByActId(Integer actId) throws Exception;
	
	/**
	 * 根据活动ID查询付钱定制用户数
	 * @param actId
	 * @return
	 * @throws Exception
	 */
	public Integer queryPayUserByActId(Integer actId) throws Exception;
}
