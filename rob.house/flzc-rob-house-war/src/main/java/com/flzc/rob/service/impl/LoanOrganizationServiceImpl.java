package com.flzc.rob.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.LoanOrganization;
import com.flzc.rob.api.service.LoanOrganizationService;

/**
 * 金融机构服务实现
 * @ClassName: LoanOrganizationServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年12月9日 下午8:37:22
 */
@Service("loanOrganizationService")
public class LoanOrganizationServiceImpl extends BaseServiceImpl
	implements LoanOrganizationService{

	@Override
	public LoanOrganization queryLoanOrganizationByCityId(Integer cityId) {

		String hql = "from LoanOrganization where status = 0 and cityId=:cityId";
		List<LoanOrganization> list = this.findByNamedParam(hql, "cityId", cityId);
		return (list == null || list.isEmpty())?null:list.get(0);
	}

}
