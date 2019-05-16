package com.wechat.token;

public class TokenEntity {
	
	private String userid;
	private String token;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public TokenEntity(String userid, String token) {
		this.userid = userid;
		this.token = token;
	}

	public TokenEntity() {
		this.userid = "";
		this.token = "";
	}

}
