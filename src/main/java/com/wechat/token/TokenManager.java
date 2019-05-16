package com.wechat.token;

public interface TokenManager {
	
	public TokenEntity createToken(String userid);

	public boolean checkToken(TokenEntity entity);

	public void delToken(String userid);
	
}
