package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.PropertyCustomizationItem;


/**
 * 定制选项服务接口
 * @ClassName: PropertyCustomizationItemService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午4:35:12
 */
public interface PropertyCustomizationItemService {

	/**
	 * 根据id查询定制选项服务接口
	 * @Title: queryPropertyCustomizationItemById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: List<Integer>
	 */
	public PropertyCustomizationItem queryPropertyCustomizationItemById(Integer id);
}
