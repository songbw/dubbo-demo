package com.flzc.rob.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.QuestionActivityParticipant;
import com.flzc.rob.api.service.QuestionActivityParticipantService;
import com.flzc.rob.util.Constant;

/**
 * 答题活动参与人信息服务实现
 * @ClassName: QuestionActivityParticipantServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午5:29:13
 */
@Service("questionActivityParticipantService")
public class QuestionActivityParticipantServiceImpl extends BaseServiceImpl implements QuestionActivityParticipantService{
	
	private final static Logger logger = LoggerFactory.getLogger(QuestionActivityParticipantServiceImpl.class);
	
	@Resource(name = "commonDao")
    private CommonDao commonDao;

	@Override
	public Integer saveQuestionActivityParticipant(QuestionActivityParticipant pant) {

		try{
			
			return Integer.valueOf(this.save(pant).toString());
			
		}catch(Exception e){
			logger.error("保存", e);
			throw e;
		}
	}

	@Override
	public List<QuestionActivityParticipant> queryQAPByActId(Integer actId,Integer curPage,Integer pageSize) {

		if(curPage==null || curPage<=0) curPage=1;
		pageSize = Constant.DEFAULT_PAGESIZE;
		
		String hql = "from QuestionActivityParticipant where actId=:actId";
		List<QuestionActivityParticipant> list = this.findByNamedParam(
				hql, new String[]{"actId"}, new Object[]{actId},curPage,pageSize);
		return list;
	}

	@Override
	public List<QuestionActivityParticipant> queryQAPByUserId(Integer userId,Integer actId) {

		String hql = "from QuestionActivityParticipant where userId=:userId and actId=:actId";
		List<QuestionActivityParticipant> list = this.findByNamedParam(
				hql, new String[]{"userId","actId"}, new Object[]{userId,actId});
		return list;
	}

	@Override
	public QuestionActivityParticipant queryQuestionActivityParticipantById(Integer id) {

		return this.findById(QuestionActivityParticipant.class, id);
	}

	@Override
	public Integer queryCountByActId(Integer actId) {

		String hql = "select count(id) from QuestionActivityParticipant where actId=:actId group by userId ";
		List<Long> list = this.findByNamedParam(hql, "actId", actId);
		return (list == null || list.isEmpty())?0:list.size();
	}

	@Override
	public List<QuestionActivityParticipant> queryQuestionActivityParticipantByPageAndActId(Integer page,
			Integer pageSize, Integer actId) {

		String hql = "from QuestionActivityParticipant where actId = " + actId + " group by userId order by createTime desc";
		int minSize = (page - 1) * pageSize;
		List<QuestionActivityParticipant> list = this.findByHql(hql, minSize, pageSize);
		return list;
	}

	@Override
	public List<QuestionActivityParticipant> queryQuestionActivityParticipantList(
			QuestionActivityParticipant questionActivityParticipant) throws Exception {
		
		return commonDao.findObjs(questionActivityParticipant);
	}

	/**
	 * 根据查询用户参与活动的次数
	 * @param userId
	 * @param actId
	 * @return
	 */
	@Override
	public int queryCountTimes(Integer userId, Integer actId){
		String hql = "select count(t.id) from QuestionActivityParticipant t where t.userId=%1s and t.actId=%2s";
		return  this.commonDao.findCountByHql(String.format(hql,userId,actId) );
	}

	@Override
	public QuestionActivityParticipant queryQuestionActivityParticipantByUserIdAndActIdAndReward(Integer userId,
			Integer actId) {

		String hql = "from QuestionActivityParticipant where status = 0 and userId=:userId and actId=:actId";
		List<QuestionActivityParticipant> list = this.findByNamedParam(
				hql, new String[]{"userId","actId"}, new Object[]{userId,actId});
		return (list == null || list.isEmpty())?null:list.get(0);
	}
}
