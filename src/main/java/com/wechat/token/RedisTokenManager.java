package com.wechat.token;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

@Repository
public class RedisTokenManager implements TokenManager {

	public String[] getRedisIP() {
		String[] _rs = new String[2];
		String _ip = "";
		String _expire = "0";
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException ioE) {
			ioE.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		_ip = properties.getProperty("IP").toString();
		_expire = properties.getProperty("Expire").toString();
		_rs[0] = _ip;
		_rs[1] = _expire;
		return _rs;
	}

	public Boolean addRedis(String userid, String token) {
		Boolean _bR = false;
		try {
			String[] _p = getRedisIP();
			Jedis jedis = new Jedis(_p[0]);
			if (jedis.exists(token)) {// 若存在,则先清空
				jedis.del(token);
			} else {
				// jedis.set(token, userid);
				jedis.setex(token, Integer.parseInt(_p[1]), userid);
			}
			_bR = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return _bR;
	}

	public Boolean delRedis(String token) {
		Boolean _bR = false;
		try {
			String[] _p = getRedisIP();
			Jedis jedis = new Jedis(_p[0]);
			if (jedis.exists(token)) {// 若存在,则先清空
				jedis.del(token);
			}
			_bR = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return _bR;
	}

	public String selRedis(String token) {
		String _token = "";
		try {
			String[] _p = getRedisIP();
			Jedis jedis = new Jedis(_p[0]);
			if (jedis.exists(token)) {// 更新时间
				jedis.expire(token, Integer.parseInt(_p[1]));
				_token = token;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return _token;
	}

	@Override
	public TokenEntity createToken(String userid) {
		String token = UUID.randomUUID().toString().replace("-", "");
		TokenEntity entity = new TokenEntity(userid, token);
		// redis save
		addRedis(userid, token);
		return entity;
	}

	@Override
	public boolean checkToken(TokenEntity entity) {
		boolean _bR = false;
		String _token = selRedis(entity.getToken());
		if (entity.getToken().equals(_token)) {
			_bR = true;
		}
		return _bR;
	}

	@Override
	public void delToken(String token) {
		// redis del userid
		delRedis(token);
	}

}
