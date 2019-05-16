package com.wechat.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * JSON工具类
 *
 * @author WJF on 2018/09/06
 */

public class JsonUtil {

	/**
	 * JSON转换为实体类
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T json2obj(String jsonStr, Class<T> clazz) throws Exception {
		return JSON.parseObject(jsonStr, clazz);
	}
	
	/**
	 * 实体类转换为JSON
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String obj2json(Object obj) throws Exception {
		return JSON.toJSONString(obj);
	}

	/**
	 * JSON转换为Map<String, Object>
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
		return JSON.parseObject(jsonStr, Map.class);
	}

	/**
	 * Map<String, Object>转换为JSON
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T map2obj(Map<?, ?> map, Class<T> clazz) throws Exception {
		return JSON.parseObject(JSON.toJSONString(map), clazz);
	}

}
