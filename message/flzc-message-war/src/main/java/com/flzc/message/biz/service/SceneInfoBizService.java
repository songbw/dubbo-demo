package com.flzc.message.biz.service;

import com.flzc.message.biz.entity.SceneInfo;

/**
 * 案场经纪人业务接口
 * @author bing.xiao
 *
 */
public interface SceneInfoBizService {

	/**
	 * 根据邀请码查询案场经纪人
	 * @param inviteCode
	 * @return
	 * @throws Exception
	 */
	public SceneInfo querySceneInfoByInviteCode(String inviteCode) throws Exception;
}
