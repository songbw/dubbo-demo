package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.AutionGuarantee;

/**
 * 竞拍活动服务接口
 * @ClassName: AutionGuaranteeService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月14日 下午9:33:54
 */
public interface AutionGuaranteeService {

	/**
	 * 根据活动id查询竞拍活动服务保障
	 * @Title: queryAutionGuaranteeByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: AutionGuarantee
	 */
	public AutionGuarantee queryAutionGuaranteeByActId(Integer actId);
}
