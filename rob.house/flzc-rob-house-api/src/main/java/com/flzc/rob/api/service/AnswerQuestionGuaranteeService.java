package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.AnswerQuestionGuarantee;

/**
 * 答题活动服务保障
 * @ClassName: AnswerQuestionGuaranteeService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月14日 下午9:32:19
 */
public interface AnswerQuestionGuaranteeService {

	/**
	 * 根据活动id查询服务保证信息
	 * @Title: queryAnswerQuestionGuaranteeByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: AnswerQuestionGuarantee
	 */
	 public AnswerQuestionGuarantee queryAnswerQuestionGuaranteeByActId(Integer actId);
}
