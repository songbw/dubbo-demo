package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.AutionCerVerfiy;

/**
 * 竞拍身份证服务
 * @ClassName: AutionCerVerfiyService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午1:41:46
 */
public interface AutionCerVerfiyService {

	/**
	 * 保存竞拍活动用户身份证信息
	 * @Title: saveAutionCerVerfiy 
	 * @Description: TODO
	 * @param verfiy
	 * @return
	 * @return: boolean
	 */
	public Integer saveAutionCerVerfiy(AutionCerVerfiy verfiy);
	
	/**
	 * 根据条件查询竞拍身份证信息
	 * @Title: queryAutionCerVerfiy 
	 * @Description: TODO
	 * @param verfiy
	 * @return
	 * @return: AutionCerVerfiy
	 */
	public AutionCerVerfiy queryAutionCerVerfiy(AutionCerVerfiy verfiy);
	
	
	/**
	 * 根据id查询竞拍身份证信息
	 * @Title: queryAutionCerVerfiyById 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: AutionCerVerfiy
	 */
	public AutionCerVerfiy queryAutionCerVerfiyById(Integer id);
}
