package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.QuestionParticipantReward;

/**
 * 答题用户抽奖服务
 * @ClassName: QuestionParticipantRewardService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午3:03:30
 */
public interface QuestionParticipantRewardService {

	/**
	 * 保存用户答题信息
	 * @Title: saveQuestionParticipantReward 
	 * @Description: TODO
	 * @param reward
	 * @return
	 * @return: boolean
	 */
	public Integer saveQuestionParticipantReward(QuestionParticipantReward reward);
	
	/**
	 * 根据活动id查询用户答题列表信息
	 * @Title: queryQuestionParticipantRewardListByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: List<QuestionParticipantReward>
	 */
	public List<QuestionParticipantReward> queryQuestionParticipantRewardListByActId(
			Integer actId,Integer curPage,Integer pageSize);
	
	/**
	 * 根据用户id查询用户答题列表信息
	 * @Title: queryQuestionParticipantRewardByUserId 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: List<QuestionParticipantReward>
	 */
	public List<QuestionParticipantReward> queryQuestionParticipantRewardByUserId(
			Integer userId);
	
	/**
	 * 根据id查询答题用户抽奖信息
	 * @Title: queryQuestionParticipantRewardById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: QuestionParticipantReward
	 */
	public QuestionParticipantReward queryQuestionParticipantRewardById(Integer id);
	
	/**
	 * 根据用户id答题活动id查询答题用户抽奖中奖信息
	 * @param userId
	 * @param actId
	 * @return
	 */
	public QuestionParticipantReward queryQuestionParticipantRewardByUserId(Integer userId,Integer actId);

	/**
	 * 统计获奖人数
	 * @Title: queryRewardCountByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: int
	 */
	public int queryRewardCountByActId(Integer actId);
	
	/**
	 * 根据活动id和档位id查询数量
	 * @Title: queryRewardCountByActIdAndRewardId 
	 * @Description: TODO
	 * @param actId
	 * @param rewardId
	 * @return
	 * @return: int
	 */
	public int queryRewardCountByActIdAndRewardId(Integer actId,Integer rewardId);
}
