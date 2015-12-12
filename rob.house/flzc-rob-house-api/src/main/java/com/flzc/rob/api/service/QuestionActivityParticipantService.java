package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.QuestionActivityParticipant;

/**
 * 答题活动参与人信息服务
 * @ClassName: QuestionActivityParticipantService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午1:48:49
 */
public interface QuestionActivityParticipantService {

	/**
	 * 保存答题活动参与人信息服务
	 * @Title: saveQuestionActivityParticipant 
	 * @Description: TODO
	 * @param pant
	 * @return
	 * @return: boolean
	 */
	public Integer saveQuestionActivityParticipant(QuestionActivityParticipant pant);
	
	/**
	 * 根据活动id查询答题活动参与人信息服务
	 * @Title: queryQAPByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: List<QuestionActivityParticipant>
	 */
	public List<QuestionActivityParticipant> queryQAPByActId(Integer actId,Integer curPage,Integer pageSize);
	
	/**
	 * 根据用户id查询答题活动参与人信息服务列表
	 * @Title: queryQAPByUserId 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: List<QuestionActivityParticipant>
	 */
	public List<QuestionActivityParticipant> queryQAPByUserId(Integer userId,Integer actId);
	
	/**
	 * 根据id查询答题活动参与人
	 * @Title: queryQuestionActivityParticipantById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: QuestionActivityParticipant
	 */
	public QuestionActivityParticipant queryQuestionActivityParticipantById(Integer id);
	
	/**
	 * 根据活动id查询参与人数
	 * @Title: queryCountByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: Integer
	 */
	public Integer queryCountByActId(Integer actId);
	
	/**
	 * 根据活动id分页查询人数
	 * @Title: queryQuestionActivityParticipantByPageAndActId 
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param actId
	 * @return
	 * @return: List<QuestionActivityParticipant>
	 */
	public List<QuestionActivityParticipant> queryQuestionActivityParticipantByPageAndActId(
			Integer page,Integer pageSize,Integer actId);
	
	/**
	 * 根据条件查询活动参与人数
	 * @Title: queryQuestionActivityParticipantList
	 * @param questionActivityParticipant
	 * @return
	 * @throws Exception 
	 */
	public List<QuestionActivityParticipant> queryQuestionActivityParticipantList(QuestionActivityParticipant questionActivityParticipant) throws Exception;

	int queryCountTimes(Integer userId, Integer actId);
	
	/**
	 * 查询用户答题正确的题目
	 * @Title: queryQuestionActivityParticipantByUserIdAndActIdAndReward 
	 * @Description: TODO
	 * @param userId
	 * @param actId
	 * @return
	 * @return: QuestionActivityParticipant
	 */
	public QuestionActivityParticipant queryQuestionActivityParticipantByUserIdAndActIdAndReward(Integer userId,Integer actId);
}
