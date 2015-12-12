package com.flzc.rob.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.AutionPrice;
import com.flzc.rob.api.service.AutionPriceService;

/**
 * 用户参与竞拍业务实现
 * @ClassName: AutionPriceServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午5:23:26
 */
@Service("autionPriceService")
public class AutionPriceServiceImpl extends BaseServiceImpl implements AutionPriceService{

	private final static Logger logger = LoggerFactory.getLogger(AutionPriceServiceImpl.class);
	
	@Override
	public List<AutionPrice> queryAutionPriceByActivityId(Integer activityId) {

		String hql = "from AutionPrice where activityId=:activityId";
		List<AutionPrice> list = this.findByNamedParam(hql, "activityId", activityId);
		return list;
	}

	@Override
	public Integer saveAutionPrice(AutionPrice aution) {

		try{
			
			return Integer.valueOf(this.save(aution).toString());
			
		}catch(Exception e){
			logger.error("用户竞拍保存失败",e);
			throw e;
		}
	}

	@Override
	public AutionPrice queryAutionPriceById(Integer id) {

		return this.findById(AutionPrice.class, id);
	}

	@Override
	public Integer queryAutionPriceCountByActId(Integer actId) {

		String hql = "select count(DISTINCT userId) from AutionPrice where activityId=:actId";
		List list = this.findByNamedParam(hql, "actId", actId);
		return (list == null || list.isEmpty())?0:Integer.valueOf(list.get(0).toString());
	}

	@Override
	public List<AutionPrice> queryAutionPriceByActIdAndPage(Integer page, Integer pageSize, Integer actId) {

		String hql = "from AutionPrice where activityId = " +actId+ " group by userId order by createTime desc";
		int minSize = (page - 1) * pageSize;
		List<AutionPrice> list = this.findByHql(hql, minSize, pageSize);
		return list;
	}

	@Override
	public List<AutionPrice> queryAutionPriceByUserIdAndActId(Integer userId, Integer activityId) {

		String hql = "from AutionPrice where userId=:userId and activityId=:activityId";
		List<AutionPrice> list = this.findByNamedParam(hql, new String[]{"userId","activityId"}, new Object[]{userId,activityId});
		return list;
	}

	@Override
	public AutionPrice queryRewardUserByActId(Integer actId) {

		String hql = "from AutionPrice where activityId=:activityId and winner = 1";
		List<AutionPrice> list = this.findByNamedParam(hql, "activityId", actId);
		return (list == null || list.isEmpty())?null:list.get(0);
	}

}
