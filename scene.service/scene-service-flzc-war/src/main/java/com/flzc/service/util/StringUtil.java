package com.flzc.service.util;

import com.flzc.base.util.Memcached;
import com.flzc.service.common.Constant;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	

	/**
	 * 生成新图片地址
	 * @param imageUrl
	 * @param size
	 * @return
	 */
	public static String createNewImageUrl(String imageUrl,Integer size){
		
		if(imageUrl == null || "".equals(imageUrl))
			return "";
		
		if(size == null)
			return Constant.IMAGE_URL + imageUrl;
		
		int index = imageUrl.lastIndexOf(".");
		
		if(index < 0)
			return "";
		
		return Constant.IMAGE_URL + imageUrl.substring(0, index) + "_" + size + imageUrl.substring(index);
	}
	
	/**
	 * 密码正则验证
	 * @Title: checkPhoneNumber 
	 * @Description: TODO
	 * @param phoneNumber
	 * @return
	 * @return: boolean
	 */
	public static boolean checkPhoneNumber(String phoneNumber){
		
		Pattern pattern = Pattern.compile(Constant.PHONE_REGULAR);
		Matcher matcher = pattern.matcher(phoneNumber);  
		return matcher.matches();
	}


	public static void main(String[] args) {
//		try {
//			boolean flag = Memcached.remove("user.userid.17");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 30); //minus number would decrement the days
		Date a = cal.getTime();
		System.out.println(new Date());
	}
}
