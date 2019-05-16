package com.wechat.util;

/**
 * 全局设置
 *
 * @author Lovelyz on 2019/03/21
 */

public class Config {

	/**
	 * 版本号
	 */
	public static final String VERSION = "1.0";
	
	/** 
	 * 各个数据表ID的标识
	 */
	public static final String TODAY = "TODAY"; // 今天吃什么表
	public static final String DZ = "DZ"; // 点赞表
	public static final String MENU = "ME"; // 菜品表
	public static final String SIGN_YQ= "YQ"; // 乐器表
	public static final String SIGN_ADMIN = "AD"; // 管理員表
	public static final String SIGN_USER = "YH"; // 用户表

	public static final String SIGN_PROFESSION = "ZY"; // 专业表

	public static final String SIGN_STUDENT = "XS"; // 学生表

	public static final String SIGN_LEVEL = "KSJB"; // 考试级别表

	public static final String SIGN_ACHIEVEMENT = "CJDC"; // 成绩档次表

	public static final String SIGN_COUNTRY = "GJ"; // 国家表

	public static final String SIGN_NATION = "MZ"; // 民族表




	/**
	 * 短信相关数据
	 */
	public static final String SMS_KEYID = "11";
	public static final String SMS_KEYSECRET = "1";
	public static final String SMS_SIGNNAME = "1"; // 短信签名
	public static final String SMS_TEMPLATECODE = "1"; // 短信模板 注册时
	public static final String SMS_TEMPLATECODEFORGETPASSWORD = "1"; // 短信模板 忘记密码时
	
}
