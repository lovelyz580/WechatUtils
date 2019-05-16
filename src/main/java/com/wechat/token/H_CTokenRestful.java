package com.wechat.token;

import com.wechat.Authorization;
import com.wechat.token.CUser;
import com.wechat.token.TBase;
import com.wechat.token.TokenEntity;
import com.wechat.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rest")
public class H_CTokenRestful {

	@Autowired
	private TokenManager redisTokenManager;

	@ResponseBody
	@RequestMapping("/login")
	public TBase<TokenEntity> login(@RequestBody TBase<CUser> o) {
		TBase<TokenEntity> _r = new TBase<TokenEntity>();
		_r.setSuccess(true);
		_r.setMessage("");

		// 用户验证
		CUser _u = o.getData().get(0);
		// _u.getName(); // ---此为用户帐号, 唯一
		List<TokenEntity> data = new ArrayList<TokenEntity>();
		TokenEntity entity = redisTokenManager.createToken(_u.getName());
		data.add(entity);
		_r.setData(data);

		return _r;
	}

	@ResponseBody
	@RequestMapping("/logout")
	public TBase<TokenEntity> logout(@RequestBody TBase<TokenEntity> o) {
		TBase<TokenEntity> _r = new TBase<TokenEntity>();
		_r.setSuccess(true);
		_r.setMessage("delete userid.");

		TokenEntity _t = o.getData().get(0);

		redisTokenManager.delToken(_t.getToken());

		return _r;
	}

	@ResponseBody
	@RequestMapping("/b1")
	@Authorization
	public TBase<TokenEntity> b1(@RequestBody TBase<CUser> o) {
		TBase<TokenEntity> _r = new TBase<TokenEntity>();
		_r.setSuccess(true);
		_r.setMessage("");

		System.out.println("b1 do.");
		return _r;
	}

}
