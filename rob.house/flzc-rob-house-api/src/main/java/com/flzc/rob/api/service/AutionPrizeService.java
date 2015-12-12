package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.AutionPrize;

/**
 * 竞拍奖品设置服务接口
 * @ClassName: AutionPrizeService 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月14日 下午9:35:03
 */
public interface AutionPrizeService {

	/**
	 * 根据活动id查询竞拍奖品信息
	 * @Title: queryAutionPrizeByActId 
	 * @Description: TODO
	 * @param actId
	 * @return
	 * @return: AutionPrize
	 */
	public AutionPrize queryAutionPrizeByActId(Integer actId);
}
