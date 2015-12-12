package com.flzc.rob.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.AnswerQuestionGuarantee;
import com.flzc.rob.api.service.AnswerQuestionGuaranteeService;

/**
 * 活动服务保证接口实现
 * @ClassName: AnswerQuestionGuaranteeService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月14日 下午9:29:36
 */
@Service("answerQuestionGuaranteeService")
public class AnswerQuestionGuaranteeServiceImpl extends BaseServiceImpl implements AnswerQuestionGuaranteeService{

	@Override
	public AnswerQuestionGuarantee queryAnswerQuestionGuaranteeByActId(Integer actId) {

		String hql = "from AnswerQuestionGuarantee where actId=:actId";
		List<AnswerQuestionGuarantee> list = this.findByNamedParam(
				hql, "actId", actId);
		return (list == null || list.isEmpty())?null:list.get(0);
	}

	
}
