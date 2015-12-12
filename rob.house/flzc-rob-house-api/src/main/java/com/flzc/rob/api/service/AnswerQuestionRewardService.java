package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.AnswerQuestionReward;

/**
 * 答题活动奖项设置业务
 * @ClassName: AnswerQuestionRewardService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午1:29:57
 */
public interface AnswerQuestionRewardService {

	/**
	 * 保存活动奖项设置
	 * @Title: saveAnswerQuestionReward 
	 * @Description: TODO
	 * @param answerQuestionReward
	 * @return
	 * @return: boolean
	 */
	public Integer saveAnswerQuestionReward(AnswerQuestionReward answerQuestionReward);
	
	/**
	 * 根据活动id查询活动奖项设置列表
	 * @Title: queryAnswerQuestionRewardListByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: List<AnswerQuestionReward>
	 */
	public List<AnswerQuestionReward> queryAnswerQuestionRewardListByActId(Integer actId);
	
	/**
	 * 根据id查询奖项设置
	 * @Title: queryAnswerQuestionRewardById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: AnswerQuestionReward
	 */
	public AnswerQuestionReward queryAnswerQuestionRewardById(Integer id);

	/**
	 * 查询最高奖项
	 * @param actId
	 * @return
	 * @throws Exception
	 */
	AnswerQuestionReward queryTopRewardByActId(Integer actId) throws Exception;
}
