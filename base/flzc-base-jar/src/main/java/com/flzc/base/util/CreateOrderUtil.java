package com.flzc.base.util;

/**
 * 生成订单工具类
 * @author james
 *
 */
public class CreateOrderUtil{
	
	public static String createOrderNo(Integer userId) throws Exception{
		
		return Constants.PREFIX+DateUtil.getCurrentDateTimeStr()+userId;
	}
}
