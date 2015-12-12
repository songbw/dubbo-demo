package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.QuestionParticipantReward;
import com.flzc.rob.api.service.QuestionParticipantRewardService;
import com.flzc.rob.util.Constant;

/**
 * 答题用户抽奖服务实现
 * @ClassName: QuestionParticipantRewardServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午5:42:11
 */
@Service("questionParticipantRewardService")
public class QuestionParticipantRewardServiceImpl extends BaseServiceImpl implements QuestionParticipantRewardService{

	private final static Logger logger = LoggerFactory.getLogger(QuestionParticipantRewardServiceImpl.class);
	
	@Override
	public Integer saveQuestionParticipantReward(QuestionParticipantReward reward) {

		try{
			
			return Integer.valueOf(this.save(reward).toString());
			
		}catch(Exception e){
			
			logger.error("保存答题用户抽奖失败", e);
			throw e;
		}
	}

	@Override
	public List<QuestionParticipantReward> queryQuestionParticipantRewardListByActId(
			Integer actId,Integer curPage,Integer pageSize) {
		
		if(curPage==null || curPage<=0) curPage=1;
		pageSize = Constant.DEFAULT_PAGESIZE;
		
		String hql = "from QuestionParticipantReward where actId=:actId";
		List<QuestionParticipantReward> list = this.findByNamedParam(hql, new String[]{"actId"}, new Object[]{actId},(curPage-1)*pageSize,pageSize);
		return list;
	}

	@Override
	public List<QuestionParticipantReward> queryQuestionParticipantRewardByUserId(
			Integer userId) {

		String hql = "from QuestionParticipantReward where userId=:userId";
		List<QuestionParticipantReward> list = this.findByNamedParam(hql, "userId", userId);
		return list;
	}

	@Override
	public QuestionParticipantReward queryQuestionParticipantRewardById(Integer id) {

		return this.findById(QuestionParticipantReward.class, id);
	}

	@Override
	public QuestionParticipantReward queryQuestionParticipantRewardByUserId(Integer userId, Integer actId) {
		
		String hql = "from QuestionParticipantReward where userId=:userId and actId=:actId";
		List<QuestionParticipantReward> list = this.findByNamedParam(hql, new String[]{"userId","actId"}, new Object[]{userId,actId});
		if(list== null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public int queryRewardCountByActId(Integer actId) {

		String hql = "select count(id) from QuestionParticipantReward where actId=:actId";
		List<Long> list = this.findByNamedParam(hql, "actId", actId);
		return (list==null || list.isEmpty())?0:Integer.valueOf(list.get(0).toString());
	}

	@Override
	public int queryRewardCountByActIdAndRewardId(Integer actId, Integer rewardId) {

		String hql = "select count(id) from QuestionParticipantReward where actId=:actId and rewardId=:rewardId";
		List<Long> list = this.findByNamedParam(hql, new String[]{"actId","rewardId"}, new Object[]{actId,rewardId});
		return (list == null || list.isEmpty())?0:Integer.valueOf(list.get(0).toString());
	}
}
