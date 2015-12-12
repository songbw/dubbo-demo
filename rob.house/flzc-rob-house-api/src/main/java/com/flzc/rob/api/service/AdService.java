package com.flzc.rob.api.service;

import java.util.List;
import java.util.Map;

/**
 * 广告
 * @ClassName: AdService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年12月6日 下午2:59:16
 */
public interface AdService {

	/**
	 * 根据城市查询用户app广告
	 * @Title: queryUserAPPAdByCity 
	 * @Description: TODO
	 * @param cityId
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String, Object>> queryUserAPPAdByCity(Integer cityId);
}
