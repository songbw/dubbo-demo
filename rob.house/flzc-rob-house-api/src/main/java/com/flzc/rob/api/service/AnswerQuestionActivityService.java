package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.AnswerQuestionActivity;

/**
 * 答题活动服务
 * @ClassName: AnswerQuestionActivityService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午12:20:43
 */
public interface AnswerQuestionActivityService {

	/**
	 * 保存答题活动
	 * @Title: saveAnswerQuestionActivity 
	 * @Description: TODO
	 * @param activity
	 * @return
	 * @return: boolean
	 */
	public Integer saveAnswerQuestionActivity(AnswerQuestionActivity activity);
	
	/**
	 * 根据楼盘id查询答题活动
	 * @Title: queryAnswerQuestionActivityByBuildingId 
	 * @Description: TODO
	 * @param buildingId
	 * @return
	 * @return: AnswerQuestionActivity
	 */
	public AnswerQuestionActivity queryAnswerQuestionActivityByBuildingId(Integer buildingId);
	
	/**
	 * 根据id查询答题活动信息
	 * @Title: queryAnswerQuestionActivityById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: AnswerQuestionActivity
	 */
	public AnswerQuestionActivity queryAnswerQuestionActivityById(Integer id);
	
	/**
	 * 获取房链券使用规则
	 * @Title: queryTicketUseRule
	 * @param ticketId
	 * @param activityId
	 * @return
	 */
	public String queryAnswerTicketUseRule(Integer ticketId,Integer activityId);
}
