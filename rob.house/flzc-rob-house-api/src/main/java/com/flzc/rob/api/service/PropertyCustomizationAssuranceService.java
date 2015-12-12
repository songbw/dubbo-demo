package com.flzc.rob.api.service;

import com.flzc.base.service.BaseService;
import com.flzc.rob.api.entity.PropertyCustomizationAssurance;

/**
 * 私人定制服务保障接口
 * @ClassName: PropertyCustomizationAssuranceService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月18日 下午3:41:17
 */
public interface PropertyCustomizationAssuranceService extends BaseService{

	/**
	 * 保存私人定制服务保障
	 * @Title: savePropertyCustomizationAssurance 
	 * @Description: TODO
	 * @param pca
	 * @return
	 * @return: boolean
	 */
	public boolean savePropertyCustomizationAssurance(PropertyCustomizationAssurance pca);

	/**
	 * 根据id查询私人定制服务保障
	 * @Title: queryPropertyCustomizationAssuranceById 
	 * @Description: TODO
	 * @return
	 * @return: PropertyCustomizationAssurance
	 */
	public PropertyCustomizationAssurance queryPropertyCustomizationAssuranceById(Integer id);

	/**
	 * 根据活动id查询服务定制保障
	 * @Title: queryPropertyCustomizationAssuranceByActivityId 
	 * @Description: TODO
	 * @param activityId
	 * @return
	 * @return: PropertyCustomizationAssurance
	 */
	public PropertyCustomizationAssurance queryPropertyCustomizationAssuranceByActivityId(Integer activityId);
}
