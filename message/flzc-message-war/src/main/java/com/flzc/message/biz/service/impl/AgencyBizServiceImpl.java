package com.flzc.message.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.CommonDao;
import com.flzc.message.biz.entity.Agency;
import com.flzc.message.biz.service.AgencyBizService;

@Service
public class AgencyBizServiceImpl implements AgencyBizService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Agency queryAgencyByInviteCode(String inviteCode) throws Exception {
		try {
			Agency agency = new Agency();
			agency.setInviteCode(inviteCode);
            return  commonDao.findUniqueObj(agency);
		} catch (Exception e) {
			throw e;
		}
	}

}
