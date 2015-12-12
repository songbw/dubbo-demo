package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.AnswerQuestionReward;
import com.flzc.rob.api.service.AnswerQuestionRewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 答题活动奖项设置业务接口实现类
 * @ClassName: AnswerQuestionRewardServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午4:46:23
 */
@Service("answerQuestionRewardService")
public class AnswerQuestionRewardServiceImpl  implements AnswerQuestionRewardService{

	private final static Logger logger = LoggerFactory.getLogger(AnswerQuestionRewardServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Integer saveAnswerQuestionReward(AnswerQuestionReward answerQuestionReward) {

		try{
			
			return Integer.valueOf(this.baseDao.save(answerQuestionReward).toString());
			
		}catch(Exception e){
			logger.error("保存答题活动奖项设置", e);
			throw e;
		}
	}

	@Override
	public List<AnswerQuestionReward> queryAnswerQuestionRewardListByActId(Integer actId) {

		String hql = "from AnswerQuestionReward r where r.actId=:actId order by r.levelValue desc ";
		List<AnswerQuestionReward> list = this.baseDao.findByNamedParam(hql, "actId", actId);
		return list;
	}

	@Override
	public AnswerQuestionReward queryAnswerQuestionRewardById(Integer id) {

		return this.baseDao.findById(AnswerQuestionReward.class, id);
	}

	/**
	 * 查询活动的最高奖项
	 * @param actId
	 * @return
	 * @throws Exception
	 */
    @Override
	public AnswerQuestionReward queryTopRewardByActId(Integer actId) throws Exception {
		AnswerQuestionReward param = new AnswerQuestionReward();
		param.setActId(actId);
		String hql = " from AnswerQuestionReward r order by r.levelValue desc ";
		try {
			List<AnswerQuestionReward> row = this.baseDao.findByHql(hql, 1);
			if(row!=null && !row.isEmpty())
				return row.get(0) ;
			return null;
		} catch (Exception e) {
			logger.error("",e);
			throw e;
		}
	}
}
