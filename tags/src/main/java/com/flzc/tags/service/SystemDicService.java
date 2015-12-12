package com.flzc.tags.service;

import com.flzc.base.service.BaseService;
import com.flzc.tags.bean.SystemDic;
import java.util.List;

public abstract interface SystemDicService extends BaseService {
	public abstract List<SystemDic> findChildDicByParentCode(String paramString);

	public abstract SystemDic findSystemDicByCode(String paramString);

	public abstract SystemDic findSystemDicByValue(String paramString1, String paramString2);

	public abstract List<SystemDic> getSystemDics(String paramString);

	public abstract SystemDic getSystemDic(String paramString);
}
