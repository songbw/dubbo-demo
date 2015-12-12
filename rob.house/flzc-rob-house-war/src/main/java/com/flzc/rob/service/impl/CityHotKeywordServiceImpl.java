package com.flzc.rob.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.BaseDao;
import com.flzc.rob.api.entity.CityHotKeyword;
import com.flzc.rob.api.service.CityHotKeywordService;

@Service("cityHotKeywordService")
public class CityHotKeywordServiceImpl implements CityHotKeywordService {
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public List<CityHotKeyword> queryCityHotKeyword(String cityName) throws Exception {
		String hql = "from CityHotKeyword where cityName like '%"+cityName+"%' and state=0 order by orderIndex asc";
		return baseDao.findByHql(hql);
	}
}
