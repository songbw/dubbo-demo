package com.flzc.message.biz.service;

import com.flzc.message.biz.entity.Agency;


/**
 * 经纪人业务接口
 * @author bing.xiao
 *
 */
public interface AgencyBizService {

	/**
	 * 根据邀请码查询经纪人
	 * @param inviteCode
	 * @return
	 * @throws Exception
	 */
	public Agency queryAgencyByInviteCode(String inviteCode) throws Exception;
}
