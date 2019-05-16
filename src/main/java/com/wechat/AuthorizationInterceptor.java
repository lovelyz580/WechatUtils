package com.wechat;

import com.wechat.token.TokenEntity;
import com.wechat.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenManager redisTokenManager;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (method.getAnnotation(Authorization.class) != null) {
			String _token = request.getHeader("token").toString();// request.getParameter("token").toString();

			// 此处验证token
			TokenEntity _te = new TokenEntity("", _token);
			Boolean _r = redisTokenManager.checkToken(_te);
			if (_r) {
				System.out.println("token success.");
			} else {
				System.out.println("token failed.");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = null;
				try {
					String _outStr = String.format("{\"result\":\"%b\",\"token\":\"%s\"}", _r, _token);
					out = response.getWriter();
					out.append(_outStr);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
			}

			return _r;
		}

		return true;
	}
	
}
