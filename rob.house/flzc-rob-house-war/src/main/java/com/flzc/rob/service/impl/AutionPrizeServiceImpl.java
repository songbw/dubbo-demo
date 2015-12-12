package com.flzc.rob.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.AutionPrize;
import com.flzc.rob.api.service.AutionPrizeService;

/**
 * 竞拍奖品设置接口实现
 * @ClassName: AutionPrizeServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月14日 下午9:48:17
 */
@Service("autionPrizeService")
public class AutionPrizeServiceImpl extends BaseServiceImpl implements AutionPrizeService{

	@Override
	public AutionPrize queryAutionPrizeByActId(Integer actId) {

		String hql = "from AutionPrize where actId=:actId";
	 	List<AutionPrize> list = this.findByNamedParam(hql, "actId", actId);
		return (list == null || list.isEmpty())?null:list.get(0);
	}

}
