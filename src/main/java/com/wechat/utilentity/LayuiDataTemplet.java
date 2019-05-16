package com.wechat.utilentity;

import java.util.List;

/**
 * 通用数据模板-带数据List
 * 
 * code(默认为0)
 * 
 * count(数据的数量)
 * 
 * msg(消息:"找到相关数据"，"未找到相关数据"...)
 * 
 * data(数据List)
 * 
 * 返回实体类
 * 
 * @author WJF on 2018/09/03
 */

public class LayuiDataTemplet<T> {
	
	/**
	 * 默认为0
	 */
	private int code;
	
	/**
	 * 数据的数量
	 */
	private int count;
	
	/**
	 * 消息:"找到相关数据"，"未找到相关数据"...
	 */
	private String msg;

	public T getOnedata() {
		return onedata;
	}

	public void setOnedata(T onedata) {
		this.onedata = onedata;
	}

	private T onedata;
	/**
	 * 数据List集合
	 */
	private List<T> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
