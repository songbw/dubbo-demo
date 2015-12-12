package com.flzc.message.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.CommonDao;
import com.flzc.message.biz.entity.User;
import com.flzc.message.biz.service.UserBizService;

@Service
public class UserBizServiceImpl implements UserBizService{
	
	@Autowired
	private CommonDao commonDao;

	@Override
	public User queryUserByInviteCode(String inviteCode) throws Exception {
		try {
			User user = new User();
			user.setInviteCode(inviteCode);
            return  commonDao.findUniqueObj(user);
		} catch (Exception e) {
			throw e;
		}
	}

}
