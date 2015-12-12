package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.QuestionItems;

/**
 * 活动答题服务
 * @ClassName: QuestionItemsService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午2:53:55
 */
public interface QuestionItemsService {

	/**
	 * 保存活动答题服务
	 * @Title: saveQuestionItems 
	 * @Description: TODO
	 * @param items
	 * @return
	 * @return: boolean
	 */
	public Integer saveQuestionItems(QuestionItems items);
	
	/**
	 * 根据活动id查询答题列表
	 * @Title: queryQuestionItemsByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: List<QuestionItems>
	 */
	public List<QuestionItems> queryQuestionItemsByActId(Integer actId);
	
	/**
	 * 根据id查询题库
	 * @Title: queryQuestionItemsById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: QuestionItems
	 */
	public QuestionItems queryQuestionItemsById(Integer id);
}
