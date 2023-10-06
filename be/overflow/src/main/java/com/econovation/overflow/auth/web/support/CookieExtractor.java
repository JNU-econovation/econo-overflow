package com.econovation.overflow.auth.web.support;

import com.econovation.overflow.auth.domain.exception.AuthorizationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class CookieExtractor implements TokenExtractor {

	@Override
	public String extract(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("refreshToken")) {
				String token = cookie.getValue();
				validNullToken(token);
				return token;
			}
		}
		throw new AuthorizationException("토큰이 존재하지 않습니다.");
	}

	private void validNullToken(String token) {
		if (token == null) {
			throw new AuthorizationException("토큰이 존재하지 않습니다");
		}
	}
}
