package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.QuestionItems;
import com.flzc.rob.api.service.QuestionItemsService;

/**
 * 题库服务实现
 * @ClassName: QuestionItemsServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午5:34:23
 */
@Service("questionItemsService")
public class QuestionItemsServiceImpl extends BaseServiceImpl implements QuestionItemsService{

	private final static Logger logger = LoggerFactory.getLogger(QuestionItemsServiceImpl.class);
	
	@Override
	public Integer saveQuestionItems(QuestionItems items) {

		try{
			
			return Integer.valueOf(this.save(items).toString());
			
		}catch(Exception e){
			logger.debug("题库保存失败", e);
			throw e;
		}
	}

	@Override
	public List<QuestionItems> queryQuestionItemsByActId(Integer actId) {

		String hql = "from QuestionItems where actId=:actId";
		List<QuestionItems> list = this.findByNamedParam(hql, "actId", actId);
		return list;
	}

	@Override
	public QuestionItems queryQuestionItemsById(Integer id) {

		return this.findById(QuestionItems.class, id);
	}

}
