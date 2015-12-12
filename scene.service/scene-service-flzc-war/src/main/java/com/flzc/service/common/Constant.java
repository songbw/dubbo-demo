package com.flzc.service.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	public final static String RESULT_FIELD_STATUS = "status";//字段
	public final static String RESULT_FIELD_MSG = "msg";//消息
	public final static String RESULT_FIELD_DATA = "result";//内容
	
	public final static String NEWLINE_MARK = "/r/n";
	
	//活动类型
	public final static Integer ACTIVITY_RECAP_TYPE_1 = 39001;; //答题
	public final static Integer ACTIVITY_RECAP_TYPE_2 = 39003; //竞拍
	public final static Integer ACTIVITY_RECAP_TYPE_3 = 39002; //定制
	public final static Integer ACTIVITY_RECAP_TYPE_4 = 39004; //红包抵扣
	public final static Integer ACTIVITY_RECAP_TYPE_5 = 39005; //开发商活动

	//楼盘图片类型
	public final static Integer HOUSE_BUILDING_IMG_TYPE_0 = 0; //主图
	public final static Integer HOUSE_BUILDING_IMG_TYPE_1 = 1; //效果图
	public final static Integer HOUSE_BUILDING_IMG_TYPE_2 = 2; //样板间
	public final static Integer HOUSE_BUILDING_IMG_TYPE_3 = 3; //实景图
	public final static Integer HOUSE_BUILDING_IMG_TYPE_4 = 4; //户型图
	public final static Integer HOUSE_BUILDING_IMG_TYPE_5 = 5; //周边配套
	public final static Integer HOUSE_BUILDING_IMG_TYPE_6 = 6; //外景图
	public final static Integer HOUSE_BUILDING_IMG_TYPE_7 = 7; //交通图
	
	//楼盘价格code
	public final static String HOUSE_BUILDING_REFER_PRICE = "7000";
	//标签类型
	public final static Integer HOUSE_BUILDING_TAGS_TYPE_0 = 0;//公共
	public final static Integer HOUSE_BUILDING_TAGS_TYPE_1 = 1;//自定义
	
	//用户收藏/分享类型
	public final static Integer HOUSE_BUILDING_COLLECT=1;//收藏
	public final static Integer HOUSE_BUILDING_SHARE=2;//分享
	
	//标签code
	public final static String HOUSE_BUILDING_TAGS_CODE_HOUSE = "100";//户型
	public final static String HOUSE_BUILDING_TAGS_CODE_BUILDING_TYPE = "400";//楼盘类型
	public final static String HOUSE_BUILDING_TAGS_CODE_AROUND = "400";//周边配套
	public final static String HOUSE_BUILDING_TAGS_CODE_TESE = "200";//特色

	public final static String HOUSE_BUILDING_PRIVATE = "50000";//私人定制

	public final static Integer DEFUAL_PAGE = 1;
	public final static Integer DEFUAL_PAGESIZE = 5;
	public final static Integer DEFUAL_PAGESIZE_3 = 3;
	
	public final static String CODE_TYPE_1 = "1";//注册
	public final static String CODE_TYPE_2 = "2";//找回密码
	
	/**
	 * 初始页数
	 */
	public final static Integer init_page = 1;
	/**
	 * 首页活动条数
	 */
	public final static Integer index_activity_size = 3;
	
	public final static Integer RESULT_MSG_0 = 0; //成功
	public final static Integer RESULT_MSG_1 = 1; //失败

	public final static  Integer HOME_HEADLINE_LIMIT = 10 ;//头条显示条数
	
	/**
	 * 图片地址
	 */
	public final static String IMAGE_URL = "http://image.fanglianzhongchou.com/image";
	
	public final static Integer IMG_SIZE_190_225 = 225;
	
	public static Map<Integer, String> RESULT_MSG = null;
	
	/**
	 * 答题活动异常提示
	 */
	public final static Integer QUESTION_ACT_NOTE_0 =0; // 活动期未开始或已终止!
	public final static Integer QUESTION_ACT_NOTE_1 =1; // 该活动已经获奖!
	public final static Integer QUESTION_ACT_NOTE_2 =2; // 参与活动次数达到上限！
	public final static Integer QUESTION_ACT_NOTE_3 =3; // 活动问题均答错！
	public final static Integer QUESTION_ACT_NOTE_4 =4; // 活动没有可回答的问题！
	public final static Integer QUESTION_ACT_NOTE_5 =5; // 已经回答问题正确未抽奖！
	public final static Integer QUESTION_ACT_NOTE_6 =6; // 请回答问题！
	public final static Integer QUESTION_ACT_NOTE_7 =7; // 活动获奖人数已满！
	public final static Integer QUESTION_ACT_NOTE_8 =8; // 档位活动获奖人数已满！
	
	/**
	 * 房链券使用状态
	 */
	public static final  Integer TICKET_STATUS_0 = 0;
	public static final  Integer TICKET_STATUS_1 = 1;
	public static final  Integer TICKET_STATUS_2 = 2;
	public static final  Integer TICKET_STATUS_3 = 3;
	
	/**
	 * 房链券使用状态名称
	 */
	public static final  String TICKET_STATUS_NAME_0 = "未消费";
	public static final  String TICKET_STATUS_NAME_1 = "已消费";
	public static final  String TICKET_STATUS_NAME_2 = "已过期";
	public static final  String TICKET_STATUS_NAME_3 = "已做废";
	
	/**
	 * 房链券详情参数
	 */
	public final static Integer FLZC_TICKET_0 = 0;//正常
	public final static Integer FLZC_TICKET_1 = 1;//活动id不能为空
	public final static Integer FLZC_TICKET_2 = 2;//活动不存在
	public final static Integer FLZC_TICKET_3 = 3;//房链券id不能为空
	public final static Integer FLZC_TICKET_4 = 4;//房链券不存在
	public final static Integer FLZC_TICKET_5 = 5;//活动类型不能为空
	
	//性别状态
	public final static Integer GENDER_1001 = 1001;//男
	public final static Integer GENDER_1002 = 1002;//女
	public final static Integer GENDER_1003 = 1003;//保密
	
	//性别状态名称
	public final static String GENDER_0_NAME = "男";//男
	public final static String GENDER_1_NAME = "女";//女
	public final static String GENDER_2_NAME = "保密";//保密
	
	//用户评论评分
	public final static String STAR_601 = "601";//1星
	public final static String STAR_602 = "602";//2星
	public final static String STAR_603 = "603";//3星
	public final static String STAR_604 = "604";//4星
	public final static String STAR_605 = "605";//5星
	
	/**
	 * 订单状态
	 */
	public final static Integer ORDER_STATUS_0 = 0; //未处理
	public final static Integer ORDER_STATUS_1 = 1; //失败
	public final static Integer ORDER_STATUS_2 = 2; //成功
	public final static Integer ORDER_STATUS_3 = 3; //已关闭
	
	/**
	 * 答题回答正确
	 */
	public final static Integer QUESTION_ANSWER_RIGHT =0; // 答题正确!
	
	/**
	 * 答题活动消息
	 */
	public static Map<Integer, String> QUESTION_ACT_MSG = null;
	
	/**
	 * 注册提示
	 */
	public static Map<Integer, String> REGISTER_RESULT = null;
	
	/**
	 * 找回密码提示
	 */
	public static Map<Integer, String> FIND_RESULT = null;
	
	/**
	 * 用户中心提示
	 */
	public static Map<Integer, String> USER_ACCOUNT_RESULT = null;
	
	/**
	 * 活动类型名称
	 */
	public static Map<Integer, String> ACTIVITY_TYPE_NAME_RESULT = null;
	
	/**
	 * 房链券使用状态名称
	 */
	public static Map<Integer, String> TICKET_STATUS_NAME_RESULT = null;
	
	/**
	 * 房链券详情状态名称
	 */
	public static Map<Integer, String> TICKET_DETATIL_RESULT = null;
	
	/**
	 * 性别名称
	 */
	public static Map<Integer, String> GENDER_RESULT = null;
	
	/**
	 * 评价标签code
	 */
	public static Map<Integer, String> EVALUATE_RESULT = null;
	
	/**
	 * 订单状态
	 */
	public static Map<Integer, String> ORDER_RESULT = null;
	
	static{
		RESULT_MSG = new HashMap<Integer, String>();
		RESULT_MSG.put(0, "成功");
		RESULT_MSG.put(1, "失败");
		
		QUESTION_ACT_MSG=new HashMap<Integer, String>();
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_0, "活动期未开始或已终止!");
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_1, "该活动已经获奖!");
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_2, "参与活动次数达到上限！");
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_3, "活动问题均答错!");
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_4, "活动没有可回答的问题!");
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_5, "已经问题正确未抽奖!");
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_6, "请回答问题!");
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_7, "活动获奖人数已满!");
		QUESTION_ACT_MSG.put(QUESTION_ACT_NOTE_8, "档位活动获奖人数已满!");
		
		REGISTER_RESULT = new HashMap<Integer, String>();
		REGISTER_RESULT.put(0, "成功！");
		REGISTER_RESULT.put(1, "验证码错误，请重新输入验证码。");
		REGISTER_RESULT.put(2, "获取验证码超时，请重新获取验证码。");
		REGISTER_RESULT.put(3, "验证码已过期，请重新获取验证码。");
		REGISTER_RESULT.put(4, "该手机号已被注册，请重新输入手机号。");
		REGISTER_RESULT.put(5, "用户名已存在！");
		REGISTER_RESULT.put(6, "手机号码格式不正确！");
		REGISTER_RESULT.put(7, "手机号码不能为空！");
		REGISTER_RESULT.put(8, "系统繁忙、请稍后再试。");
		REGISTER_RESULT.put(9, "用户名不能为空！");
		REGISTER_RESULT.put(10, "密码不能为空！");
		REGISTER_RESULT.put(11, "密码格式不正确！");
		REGISTER_RESULT.put(12, "两次密码不一样！");
		REGISTER_RESULT.put(13, "异常修改");
		REGISTER_RESULT.put(14, "手机号尚未注册，请重新输入。");
		REGISTER_RESULT.put(15, "用户名不能为空。");
		REGISTER_RESULT.put(16, "手机验证码不能为空。");
		REGISTER_RESULT.put(17, "图片验证码错误，请重新输入验证码。");
		REGISTER_RESULT.put(18, "图片验证码不能为空");
		
		FIND_RESULT = new HashMap<Integer, String>();
		FIND_RESULT.put(0, "成功！");
		FIND_RESULT.put(1, "手机验证码错误，请重新输入验证码。");
		FIND_RESULT.put(2, "获取验证码超时，请重新获取验证码。");
		FIND_RESULT.put(3, "验证码已过期，请重新获取验证码。");
		FIND_RESULT.put(4, "用户不存在。");
		FIND_RESULT.put(5, "手机号码格式不正确！");
		FIND_RESULT.put(6, "手机号码不能为空！");
		FIND_RESULT.put(7, "系统繁忙、请稍后再试。");
		FIND_RESULT.put(8, "密码不能为空！");
		FIND_RESULT.put(9, "密码格式不正确！");
		FIND_RESULT.put(10, "两次密码不一样！");
		FIND_RESULT.put(11, "手机验证码不能为空。");
		FIND_RESULT.put(12, "图片验证码错误，请重新输入验证码。");
		
		USER_ACCOUNT_RESULT = new HashMap<Integer, String>();
		USER_ACCOUNT_RESULT.put(0, "正常。");
		USER_ACCOUNT_RESULT.put(1, "用户不存在。");
		USER_ACCOUNT_RESULT.put(2, "用户id不能为空。");
		USER_ACCOUNT_RESULT.put(3, "用户昵称不能为空。");
		USER_ACCOUNT_RESULT.put(4, "性别不能为空。");
		USER_ACCOUNT_RESULT.put(5, "生日不能为空。");
		USER_ACCOUNT_RESULT.put(6, "头像地址不能为空。");
		USER_ACCOUNT_RESULT.put(7, "密码不能为空。");
		USER_ACCOUNT_RESULT.put(8, "确认密码不能为空。");
		USER_ACCOUNT_RESULT.put(9, "密码不正确。");
		USER_ACCOUNT_RESULT.put(10, "两次密码不一样。");
		USER_ACCOUNT_RESULT.put(11, "手机验证码不能为空。");
		USER_ACCOUNT_RESULT.put(12, "验证码错误，请重新输入验证码。");
		USER_ACCOUNT_RESULT.put(13, "系统繁忙、请稍后再试。");
		USER_ACCOUNT_RESULT.put(14, "性别参数不是规范数值。");
		USER_ACCOUNT_RESULT.put(15, "缺少必要参数。");
		USER_ACCOUNT_RESULT.put(16, "无效tokenId值。");
		USER_ACCOUNT_RESULT.put(17, "无效年份。");
		USER_ACCOUNT_RESULT.put(18, "无效月份。");
		USER_ACCOUNT_RESULT.put(19, "无效天数。");
		USER_ACCOUNT_RESULT.put(20, "收货人名称不能为空。");
		USER_ACCOUNT_RESULT.put(21, "详细地址不能为空。");
		USER_ACCOUNT_RESULT.put(22, "用户地址不存在。");
		USER_ACCOUNT_RESULT.put(23, "地址id不能为空。");
		USER_ACCOUNT_RESULT.put(24, "手机号码不能为空。");
		USER_ACCOUNT_RESULT.put(25, "手机号码格式不正确。");
		USER_ACCOUNT_RESULT.put(26, "参数值无效。");
		USER_ACCOUNT_RESULT.put(27, "楼盘不存在。");
		USER_ACCOUNT_RESULT.put(28, "楼盘不存在。");
		USER_ACCOUNT_RESULT.put(29, "真实姓名不能为空。");
		USER_ACCOUNT_RESULT.put(30, "身份证号码不能为空。");
		USER_ACCOUNT_RESULT.put(31, "身份证号码无效。");
		
		//活动名称集合
		ACTIVITY_TYPE_NAME_RESULT = new HashMap<Integer,String>();
		ACTIVITY_TYPE_NAME_RESULT.put(ACTIVITY_RECAP_TYPE_1, "答题活动");
		ACTIVITY_TYPE_NAME_RESULT.put(ACTIVITY_RECAP_TYPE_2, "竞拍活动");
		ACTIVITY_TYPE_NAME_RESULT.put(ACTIVITY_RECAP_TYPE_3, "定制活动");
		ACTIVITY_TYPE_NAME_RESULT.put(ACTIVITY_RECAP_TYPE_4, "红包抵扣活动");
		
		//房链券使用状态
		TICKET_STATUS_NAME_RESULT= new HashMap<Integer,String>();
		TICKET_STATUS_NAME_RESULT.put(TICKET_STATUS_0, TICKET_STATUS_NAME_0);
		TICKET_STATUS_NAME_RESULT.put(TICKET_STATUS_1, TICKET_STATUS_NAME_1);
		TICKET_STATUS_NAME_RESULT.put(TICKET_STATUS_2, TICKET_STATUS_NAME_2);
		TICKET_STATUS_NAME_RESULT.put(TICKET_STATUS_3, TICKET_STATUS_NAME_3);
		
		//房链详情
		TICKET_DETATIL_RESULT = new HashMap<Integer,String>();
		TICKET_DETATIL_RESULT.put(FLZC_TICKET_0, "正常");
		TICKET_DETATIL_RESULT.put(FLZC_TICKET_1, "活动id不能为空");
		TICKET_DETATIL_RESULT.put(FLZC_TICKET_2, "活动不存在");
		TICKET_DETATIL_RESULT.put(FLZC_TICKET_3, "房链券id不能为空");
		TICKET_DETATIL_RESULT.put(FLZC_TICKET_4, "房链券不存在");
		TICKET_DETATIL_RESULT.put(FLZC_TICKET_5, "活动类型不能为空");
		
		GENDER_RESULT = new HashMap<Integer,String>();
		GENDER_RESULT.put(GENDER_1001, GENDER_0_NAME);
		GENDER_RESULT.put(GENDER_1002, GENDER_1_NAME);
		GENDER_RESULT.put(GENDER_1003, GENDER_2_NAME);
		
		EVALUATE_RESULT = new HashMap<Integer,String>();
		EVALUATE_RESULT.put(1, STAR_601);
		EVALUATE_RESULT.put(2, STAR_602);
		EVALUATE_RESULT.put(3, STAR_603);
		EVALUATE_RESULT.put(4, STAR_604);
		EVALUATE_RESULT.put(5, STAR_605);
		
		ORDER_RESULT = new HashMap<Integer, String>();
		ORDER_RESULT.put(ORDER_STATUS_0, "未付款");
		ORDER_RESULT.put(ORDER_STATUS_1, "失败");
		ORDER_RESULT.put(ORDER_STATUS_2, "已付款");
		ORDER_RESULT.put(ORDER_STATUS_3, "已关闭");
	}
	public static  final  String ACTIVITY_ANSWER = "39001";
	public static  final  String ACTIVITY_CUSTOMIZATION = "39002";
	public static  final  String AUCTION = "39003";
	
	public static final  String CUSTOMIZATION_TITLE = "接受专属定制";
	
	/**
	 * 房链券类型
	 */
	public static final  String TICKET_TYPE_NAME = "购房券";
	/**
	 * 房链券使用地
	 */
	public static final  String TICKET_USE_WAY = "购房处使用";
	
	//注册常量
	public final static Integer REGISTER_STATUS_0 = 0;//正常、
	public final static Integer REGISTER_STATUS_1 = 1;//验证码错误，请重新输入验证码。
	public final static Integer REGISTER_STATUS_2 = 2;//获取验证码超时，请重新获取验证码。
	public final static Integer REGISTER_STATUS_3 = 3;//验证码已过期，请重新获取验证码。
	public final static Integer REGISTER_STATUS_4 = 4;//该手机号已被注册，请重新输入手机号。
	public final static Integer REGISTER_STATUS_5 = 5;//用户名已存在
	public final static Integer REGISTER_STATUS_6 = 6;//手机号码格式不正确
	public final static Integer REGISTER_STATUS_7 = 7;//手机号码不能为空
	public final static Integer REGISTER_STATUS_8 = 8;//系统繁忙、请稍后再试。
	public final static Integer REGISTER_STATUS_9 = 9;//用户？？
	public final static Integer REGISTER_STATUS_10 = 10;//密码不能为空
	public final static Integer REGISTER_STATUS_11 = 11;//密码格式不正确
	public final static Integer REGISTER_STATUS_12 = 12;//两次密码不一样
	public final static Integer REGISTER_STATUS_13 = 13;//异常修改
	public final static Integer REGISTER_STATUS_14 = 14;//手机号尚未注册，请重新输入。
	public final static Integer REGISTER_STATUS_15 = 15;//用户名不能为空
	public final static Integer REGISTER_STATUS_16 = 16;//手机验证码不能为空
	public final static Integer REGISTER_STATUS_17 = 17;//验证码错误，请重新输入验证码
	public final static Integer REGISTER_STATUS_18 = 18;//图片验证码不能为空
	
	/**
	 * 过期时间
	 */
	public final static int expire_time_10 = 10;
	/**
	 * 过期时间
	 */
	public final static int expire_time_5 = 5;
	
	//找回密码常量
	public final static Integer FIND_STATUS_0 = 0;//正常、
	public final static Integer FIND_STATUS_1 = 1;//验证码错误，请重新输入验证码。
	public final static Integer FIND_STATUS_2 = 2;//获取验证码超时，请重新获取验证码。
	public final static Integer FIND_STATUS_3 = 3;//验证码已过期，请重新获取验证码。
	public final static Integer FIND_STATUS_4 = 4;//用户不存在
	public final static Integer FIND_STATUS_5 = 5;//手机号码格式不正确
	public final static Integer FIND_STATUS_6 = 6;//手机号码不能为空
	public final static Integer FIND_STATUS_7 = 7;//系统繁忙、请稍后再试。
	public final static Integer FIND_STATUS_8 = 8;//密码不能为空
	public final static Integer FIND_STATUS_9 = 9;//密码格式不正确
	public final static Integer FIND_STATUS_10 = 10;//两次密码不一样
	public final static Integer FIND_STATUS_11 = 11;//手机验证码不能为空
	public final static Integer FIND_STATUS_12 = 12;//图片验证码错误，请重新输入验证码。
	
	//手机号码正则
	public final static String PHONE_REGULAR = "^1\\d{10}$";
	//身份证号码正则
	public final static String IDCARD_REGULAR = "/^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$/";
	
	//个人中心常量
	public final static Integer USER_ACCOUNT_STATUS_0 = 0;//正常
	public final static Integer USER_ACCOUNT_STATUS_1 = 1;//用户不存在
	public final static Integer USER_ACCOUNT_STATUS_2 = 2;//用户id不能为空
	public final static Integer USER_ACCOUNT_STATUS_3 = 3;//用户昵称不能为空
	public final static Integer USER_ACCOUNT_STATUS_4 = 4;//性别不能为空
	public final static Integer USER_ACCOUNT_STATUS_5 = 5;//生日不能为空
	public final static Integer USER_ACCOUNT_STATUS_6 = 6;//头像地址不能为空
	public final static Integer USER_ACCOUNT_STATUS_7 = 7;//密码不能为空
	public final static Integer USER_ACCOUNT_STATUS_8 = 8;//确认密码不能为空
	public final static Integer USER_ACCOUNT_STATUS_9 = 9;//密码不正确
	public final static Integer USER_ACCOUNT_STATUS_10 = 10;//两次密码不一样
	public final static Integer USER_ACCOUNT_STATUS_11 = 11;//手机验证码不能为空
	public final static Integer USER_ACCOUNT_STATUS_12 = 12;//验证码错误，请重新输入验证码。
	public final static Integer USER_ACCOUNT_STATUS_13 = 13;//系统繁忙、请稍后再试。
	public final static Integer USER_ACCOUNT_STATUS_14 = 14;//性别参数不是规范数值
	public final static Integer USER_ACCOUNT_STATUS_15 = 15;//缺少必要参数
	public final static Integer USER_ACCOUNT_STATUS_16 = 16;//无效tokenId值
	public final static Integer USER_ACCOUNT_STATUS_17 = 17;//无效年份
	public final static Integer USER_ACCOUNT_STATUS_18 = 18;//无效月份
	public final static Integer USER_ACCOUNT_STATUS_19 = 19;//无效天数
	public final static Integer USER_ACCOUNT_STATUS_20 = 20;//收货人名称不能为空
	public final static Integer USER_ACCOUNT_STATUS_21 = 21;//详细地址不能为空
	public final static Integer USER_ACCOUNT_STATUS_22 = 22;//用户地址不存在
	public final static Integer USER_ACCOUNT_STATUS_23 = 23;//用户地址id不能为空
	public final static Integer USER_ACCOUNT_STATUS_24 = 24;//手机号码不能为空
	public final static Integer USER_ACCOUNT_STATUS_25 = 25;//手机号码格式不正确
	/**用户收藏、分享、浏览常量*/
	public final static Integer USER_ACCOUNT_STATUS_26 = 26;//参数值无效
	public final static Integer USER_ACCOUNT_STATUS_27 = 27;//楼盘不存在
	public final static Integer USER_ACCOUNT_STATUS_28 = 28;//图片地址不能为空
	public final static Integer USER_ACCOUNT_STATUS_29 = 29;//真实姓名不能为空
	public final static Integer USER_ACCOUNT_STATUS_30 = 30;//身份证号码不能为空
	public final static Integer USER_ACCOUNT_STATUS_31 = 31;//身份证号码无效
	
	//启用支付密码
	public final static Integer IS_ENABLE_0 = 0;//不启用
	public final static Integer IS_ENABLE_1 = 1;//启用
	
	//密码类型
	public final static Integer PASSWORD_TYPE_0 = 0;//0为登录密码
	public final static Integer PASSWORD_TYPE_1 = 1;//1为支付密码
	
	//图片状态
	public final static Integer IMAGE_TYPE_0 = 0;//0有效
	public final static Integer IMAGE_TYPE_1 = 1;//1无效
	
	//身份证认证状态
	public final static Integer IDCARD_AUTH_STATUS_0 = 0;//未认证
	public final static Integer IDCARD_AUTH_STATUS_1 = 1;//认证中
	public final static Integer IDCARD_AUTH_STATUS_2 = 2;//已认证
	
	//红包来源(58001:楼盘分享；58002：用户注册；58003：邀请好友；58004:实名认证;
	public final static Integer RED_BAG_FROM_58001 = 58001;//楼盘分享
	public final static Integer RED_BAG_FROM_58002 = 58002;//用户注册
	public final static Integer RED_BAG_FROM_58003 = 58003;//邀请好友
	public final static Integer RED_BAG_FROM_58004 = 58004;//实名认证
	
	//红包使用状态(0为未使用、1为已使用、2为过期)
	public final static Integer RED_BAG_USER_STATUS_0 = 0;
	public final static Integer RED_BAG_USER_STATUS_1 = 1;
	public final static Integer RED_BAG_USER_STATUS_2 = 2;
	
	//奖励规则
	public final static Integer RED_BAG_REWARD_1 = 1;//1元(分享)
	public final static Integer RED_BAG_REWARD_5 = 5;//5元(注册、邀请好友)
	public final static Integer RED_BAG_REWARD_8 = 8;//8元(实名认证)
	
	//收支状态
	public final static Integer IN_OUT_TYPE_0 = 0;//收入
	public final static Integer IN_OUT_TYPE_1 = 1;//支出
	
	//上传图片
	public final static String RESULT_UPLOAD_0 = "图片上传成功";
	public final static String RESULT_UPLOAD_1 = "图片对象参数不能为空";
	public final static String UPLOAD_HEAD_IMAGE_PATH = "/data/upload/headImg/";
	public final static String UPLOAD_IDCARD_IMAGE_PATH = "/data/upload/idCard/";
	
	//客户报备状态
	public final static Integer AGENCY_USER_STATUS_57001=57001;//未报备
	public final static Integer AGENCY_USER_STATUS_57002=57002;//报备中
	public final static Integer AGENCY_USER_STATUS_57003=57003;//已报备
	public final static Integer AGENCY_USER_STATUS_57004=57004;//已带看
	public final static Integer AGENCY_USER_STATUS_57005=57005;//成交
	public final static Integer AGENCY_USER_STATUS_57006=57006;//已结佣
	
	//每日分享获取红包次数
	public final static Integer SHARE_TIMES = 3;
	
	//用户评论标签分隔符
	public final static String TAGS_SPLIT = ",";
	
	public final static String PREFIX = "flzc";
}