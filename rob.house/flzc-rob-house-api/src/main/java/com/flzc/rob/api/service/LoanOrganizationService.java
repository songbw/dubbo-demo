package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.LoanOrganization;

public interface LoanOrganizationService {

	/**
	 * 根据城市查询经融公司
	 * @Title: queryLoanOrganizationByCityId 
	 * @Description: TODO
	 * @param cityId
	 * @return
	 * @return: LoanOrganization
	 */
	public LoanOrganization queryLoanOrganizationByCityId(Integer cityId);
}
