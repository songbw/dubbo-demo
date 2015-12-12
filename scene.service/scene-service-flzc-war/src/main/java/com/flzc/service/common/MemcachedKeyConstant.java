package com.flzc.service.common;

/**
 * 缓存key常量
 * @ClassName: MemcachedKeyConstant 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月22日 下午2:17:49
 */
public class MemcachedKeyConstant {

	//注册的验证码memcached
	public final static String REGISTER_CODE = "r.code.";
	
	//找回密码验证码memcached key
	public final static String REGISTER_FIND_CODE = "r.f.code.";
	
	//用户中心-安全管理-email认证memcached key
	public final static String SAFETY_EMAIL_ATTESTATION_CODE = "s.e.a.code.";
	
	//用户中心-安全管理-修改手机号码-旧号码发送验证码memcached key
	public final static String SAFETY_MOBILE_UPDATE_OLD_CODE = "s.u.p.code.";

	//用户中心-安全管理-修改手机号码-新号码发送验证码memcached key
	public final static String SAFETY_MOBILE_UPDATE_NEW_CODE = "s.m.u.code.";
	
	//用户中心-安全管理-修改支付密码memcached key
	public final static String SAFETY_UPDATE_PAY_PASSWORD_CODE = "s.u.p.p.code.";
	
	//用户中心-安全管理-设置支付密码memcached key
	public final static String SAFETY_SET_PAY_PASSWORD_CODE = "s.s.p.p.code.";
	
	//注册图片验证码memcached key
	public final static String REGISTER_IMG_CODE = "r.i.code.";

	//案场修改手机号验证码memcached key
	public final static String SCENE_MODIFY_TEL_CODE = "s.m.t.code.";
	
}
