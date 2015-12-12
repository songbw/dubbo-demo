package com.flzc.message.biz.service;

import com.flzc.message.biz.entity.User;

/**
 * 用户业务接口
 * @author bing.xiao
 *
 */
public interface UserBizService {

	/**
	 * 根据邀请码查询经纪人
	 * @param inviteCode
	 * @return
	 * @throws Exception
	 */
	public User queryUserByInviteCode(String inviteCode) throws Exception;
}
