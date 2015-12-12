package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.AnswerQuestionActivity;
import com.flzc.rob.api.service.AnswerQuestionActivityService;

/**
 * 答题活动服务实现
 * @ClassName: AnswerQuestionActivityServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午4:30:51
 */
@Service("answerQuestionActivityService")
public class AnswerQuestionActivityServiceImpl  implements AnswerQuestionActivityService{
	
	private final static Logger logger = LoggerFactory.getLogger(AnswerQuestionActivityServiceImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private CommonDao commonDao;

	@Override
	public Integer saveAnswerQuestionActivity(AnswerQuestionActivity activity) {

		try{
			
			return Integer.valueOf(baseDao.save(activity).toString());
			
		}catch(Exception e){

			logger.error("保存答题活动服务失败", e);
			throw e;
		}
	}

	@Override
	public AnswerQuestionActivity queryAnswerQuestionActivityByBuildingId(Integer buildingId) {

		String hql = "from AnswerQuestionActivity where status = 0 and buildingId=:buildingId";
		List<AnswerQuestionActivity> list = baseDao.findByNamedParam(hql, "buildingId", buildingId);
		
		if(list == null || list.isEmpty())
			return null;
		
		return list.get(0);
	}

	@Override
	public AnswerQuestionActivity queryAnswerQuestionActivityById(Integer id) {

		return baseDao.findById(AnswerQuestionActivity.class, id);
	}

	@Override
	public String queryAnswerTicketUseRule(Integer ticketId, Integer activityId) {

		String rule="";
		try {
			
			String hql = "SELECT aqa.* from FlzcTicket ft, ActivityRecap ar,AnswerQuestionActivity aqa where " +
						 "	ft.activityRecapId=ar.id " +
						 "	ar.act_id=aqa.id " +
						 " and ft.id="+ticketId+" and aqa.id="+activityId;
			
			List<AnswerQuestionActivity> list = baseDao.findByHql(hql, 1);
			if(list==null || list.isEmpty())
				return rule;
			
			return list.get(0).getRule();
		} catch (Exception e) {
			logger.error("查询房链券使用规则出错,"+e);
			throw e;
		}
	}

}
