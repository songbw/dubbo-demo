package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.CityHotKeyword;

/**
 * 搜索热词服务
 * @author bing.xiao
 *
 */
public interface CityHotKeywordService {

	/**
	 * 根据城市名查询热词
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public abstract List<CityHotKeyword> queryCityHotKeyword(String cityName) throws Exception;  
}
