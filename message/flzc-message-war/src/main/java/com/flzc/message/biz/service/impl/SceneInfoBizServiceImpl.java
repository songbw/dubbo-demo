package com.flzc.message.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.CommonDao;
import com.flzc.message.biz.entity.SceneInfo;
import com.flzc.message.biz.service.SceneInfoBizService;

@Service
public class SceneInfoBizServiceImpl implements SceneInfoBizService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Override
	public SceneInfo querySceneInfoByInviteCode(String inviteCode) throws Exception {
		try {
			SceneInfo sceneInfo = new SceneInfo();
			sceneInfo.setInviteCode(inviteCode);
            return  commonDao.findUniqueObj(sceneInfo);
		} catch (Exception e) {
			throw e;
		}
	}

}
