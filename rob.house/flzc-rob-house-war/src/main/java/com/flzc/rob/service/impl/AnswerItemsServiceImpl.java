package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.base.util.Constants;
import com.flzc.base.util.Memcached;
import com.flzc.base.util.MemcachedKeyConstant;
import com.flzc.rob.api.entity.AnswerItems;
import com.flzc.rob.api.service.AnswerItemsService;

/**
 * 答案选项接口实现类
 * @ClassName: AnswerItemsServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午3:14:28
 */
@Service("answerItemsService")
public class AnswerItemsServiceImpl extends BaseServiceImpl implements AnswerItemsService{

	private static final Logger logger = LoggerFactory.getLogger(AnswerItemsServiceImpl.class);
	
	@Override
	public Integer saveAnswerItems(AnswerItems answerItems) throws Exception{

		try {

			int id = Integer.valueOf(this.save(answerItems).toString());
			answerItems.setId(id);
			
			//答题选择项缓存
			Memcached.set(MemcachedKeyConstant.BUILDER_APPROVE
					+ id, answerItems, Constants.CACHE_TIME,
					Constants.USER_INFO_USERID_TIME);
			
			return id;
		} catch (Exception e) {
			logger.error("保存题目失败", e);
			throw e;
		}
	}

	@Override
	public List<AnswerItems> queryAnswerItemsListByQuestionId(Integer questionId) {

		String hql = "from AnswerItems where status = 0 and questionId=:questionId";
		List<AnswerItems> list = this.findByNamedParam(hql, "questionId", questionId);
		return list;
	}

	@Override
	public AnswerItems queryAnswerItems(AnswerItems items) {
		return null;
	}

	@Override
	public AnswerItems queryAnswerItemsById(Integer id) throws Exception{
		
		AnswerItems items =null;
		String key = MemcachedKeyConstant.BUILDER_APPROVE+ id;
		try {
			
			items = (AnswerItems) Memcached.get(key);
			if(items!=null)
				return items;
			
			items = this.findById(AnswerItems.class, id);
			Memcached.set(key, items, Constants.CACHE_TIME,
					Constants.USER_INFO_USERID_TIME);
		} catch (Exception e) {
			logger.error("根据id查询答案选项异常",e);
			throw e;
		}
		return items;
	}

}
