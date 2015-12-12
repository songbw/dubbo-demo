package com.flzc.base.util;

public class MemcachedKeyConstant {

    public final static String DIC = "dic."; // 字典表 根

    public static final String DIC_LIST_ALL = DIC + "list.all";// 字典表

    public final static String PROV = "prov."; // 省表 根

    public static final String PROV_LIST_ALL = PROV + "list.all";// 省表

    public final static String CITY = "city."; // 城市表 根

    public static final String CITY_LIST_ALL = CITY + "list.all";// 城市表

    public final static String AREA = "area."; // 区县表 根

    public static final String AREA_LIST_ALL = AREA + "list.all";// 区县表


    public static final String USER = "user.";//用户根

    public static final String USER_INFO_USERID = USER + "userid.";//用户信息
    
    public static final String USER_BASE_INFO_USERID = USER + "userInfo.";//用户基本信息
	
	public static final String USER_IDENTITY_USERID = USER + "identity.";//用户实名认证信息
	
	public static final String USER_HEAD_IMAGE_USERID = USER + "headImage.";//用户头像信息
	
	public static final String USER_ADDRESS_ADDRESSID = USER + "address.";//用户地址信息

    public static final String HOUSE_BUILDING_INFO = "house.building.";

    public static final String BUILDING_ACTIVITY_RECAP = "building.activity.recap.";

    public static final String BUILDING_ACTIVITY_RULE = "building.activity.rule.";
    
    public static final String BUILDING_ACTIVITY_ANSWER_RULE = "building.activity.answer.rule.";//答题选项
    
    public static final String BUILDING_ANSWER_ACTIVITY = "building.answer.activity.";//答题活动
    
    public static final String BUILDING_AROUND = "building.around.";//楼盘周边配套

    public static final String BUILDING_HOUSE_INFO = "building.house.info.";//楼盘户型
	
	public static final String USER_COLLECT_BUILDING_BY_USERID_AND_BUILDINGID = USER + "collect.buildingid.";//用户收藏楼盘

    public static final String BUILDING_TARGET_USER = "building.target.user.";//楼盘目标客户

    public static final String BUILDING_STAGE = "building.stage.";// 楼盘阶段

    public static final String BUILDING_DECORATION = "building.decoration.";// 楼盘装修

    public static final String BUILDING_MANAGER_BIZ_TYPE = "building.manager.biz.type";// 楼盘业务类型

    public static final String AGENCY_OFFICE_CODE = "agency.office.";// 楼盘阶段

    public static  final String AGENCY_LEVEL = "agency.level."; //经纪人等级

	public static final String ACTIVITY_QUESTION_ANSWER_PARTICIPANT_REWARD="act.question.answer.participant.reward.%s.$s";
    
	public static  final String AGENCY = "agency.";//经纪人
	
	public static  final String AGENCY_CERTIFICATION = "agency.certification.";//经纪人认证
	
	public static final String AGENCY_RESUME = "agency.resume.";//经纪人履历
	
	public static final String AGENCY_OFFICE = "agency.office.";//经纪人门店
	
	public static final String AGENCY_FIRM = "agency.firm.";//经纪公司
	
	public static final String AGENCY_FIRM_CERTIFICATION = "agency.firm.certification.";//经纪公司

	public static final String AGENCY_FEEDBACK = "agency.feedback.";//经纪人意见
	
	public static final String AGENCY_CLIENT = "agency.client.";//经纪人客户
	
	public static final String AGENCY_CLIENT_INTENTION_BUILDING = "agency.client.intention.building.";//经纪人客户意向楼盘
	
	public static final String AGENCY_ADDRESS = "agency.address.";//经纪人地址
	
	public static  final String BUILDER = "bulider.";//开发商
	
	public static final String BUILDER_APPROVE = "builder.approve.";//开发商认证

    public static final String HOUSE_BUILDING_TAG ="house.building.tag." ; //楼盘标签
	
}
