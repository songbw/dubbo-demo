package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.AnswerItems;

/**
 * 答案选项接口类
 * @ClassName: AnswerItemsService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 上午11:52:52
 */
public interface AnswerItemsService {

	/**
	 * 保存答案选项
	 * @Title: saveAnswerItems 
	 * @Description: TODO
	 * @param answerItems
	 * @return
	 * @return: boolean
	 * @throws Exception 
	 */
	public Integer saveAnswerItems(AnswerItems answerItems) throws Exception;
	
	/**
	 * 根据题目id查询答题选项
	 * @Title: queryAnswerItemsListByQuestionId 
	 * @Description: TODO
	 * @param questionId
	 * @return
	 * @return: List<AnswerItems>
	 */
	public List<AnswerItems> queryAnswerItemsListByQuestionId(Integer questionId);
	
	/**
	 * 根据条件查询答题选项对象
	 * @Title: queryAnswerItems 
	 * @Description: TODO
	 * @param items
	 * @return
	 * @return: AnswerItems
	 */
	public AnswerItems queryAnswerItems(AnswerItems items);
	
	/**
	 * 根据id查询答案选项
	 * @Title: queryAnswerItemsById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: AnswerItems
	 * @throws Exception 
	 */
	public AnswerItems queryAnswerItemsById(Integer id) throws Exception;
}
