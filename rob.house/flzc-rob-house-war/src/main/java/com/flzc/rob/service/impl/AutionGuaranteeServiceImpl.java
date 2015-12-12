package com.flzc.rob.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.AutionGuarantee;
import com.flzc.rob.api.service.AutionGuaranteeService;

@Service("autionGuaranteeService")
public class AutionGuaranteeServiceImpl extends BaseServiceImpl 
	implements AutionGuaranteeService{

	@Override
	public AutionGuarantee queryAutionGuaranteeByActId(Integer actId) {

		String hql = "from AutionGuarantee where actId=:actId";
		List<AutionGuarantee> list = this.findByNamedParam(hql, "actId", actId);
		return (list == null || list.isEmpty())?null:list.get(0);
	}

}
