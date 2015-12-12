package com.flzc.rob.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.AutionCerVerfiy;
import com.flzc.rob.api.service.AutionCerVerfiyService;

/**
 * 竞拍身份证表业务实现
 * @ClassName: AutionCerVerfiyServiceImpl 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年10月10日 下午5:20:18
 */
@Service("autionCerVerfiyService")
public class AutionCerVerfiyServiceImpl extends BaseServiceImpl 
	implements AutionCerVerfiyService{

	private final static Logger logger = LoggerFactory.getLogger(AutionCerVerfiyServiceImpl.class);
	
	@Override
	public Integer saveAutionCerVerfiy(AutionCerVerfiy verfiy) {

		try{
			
			return Integer.valueOf(this.save(verfiy).toString());
			
		}catch(Exception e){
			logger.error("保存竞拍身份证失败", e);
			throw e;
		}
	}

	@Override
	public AutionCerVerfiy queryAutionCerVerfiy(AutionCerVerfiy verfiy) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AutionCerVerfiy queryAutionCerVerfiyById(Integer id) {

		return this.findById(AutionCerVerfiy.class, id);
	}

}
