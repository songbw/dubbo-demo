package com.flzc.base.util;

/**
 * 缓存key
 * @ClassName: AccountMemcachedKeyConstant 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年11月30日 下午8:47:37
 */
public class AccountMemcachedKeyConstant {

	public static final String ACCOUNT_COMMON_KEY = "account.";
	
	/**
	 * 账户信息
	 */
	public static final String ACCOUNT_KEY_USERID = ACCOUNT_COMMON_KEY + "userid.";
	
	
	/**
	 * 经纪人公共key
	 */
	public static final String AGENCY_ACCOUNT_COMMON_KEY = "agency.account.";
	
	/**
	 * 经纪人账户key
	 */
	public static final String AGENCY_ACCOUNT_KEY_AGENCYID = AGENCY_ACCOUNT_COMMON_KEY + ".agencyid.";
	
	/**
	 * 根据id key
	 */
	public static final String AGENCY_ACCOUNT_KEY_ID = AGENCY_ACCOUNT_COMMON_KEY + ".id";
	
	/**
	 * 红包 agencyId key
	 */
	public static final String AGENCY_REDBAG_ACCOUNT_AGENCYID = AGENCY_ACCOUNT_COMMON_KEY + ".hongbao.agencyid.";
	
	/**
	 * 5分钟
	 */
	public static final int ACCOUNT_TIME_5 = 5;
}
