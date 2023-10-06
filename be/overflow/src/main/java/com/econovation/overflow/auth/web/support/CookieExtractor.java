package com.econovation.overflow.auth.web.support;

import com.econovation.overflow.auth.domain.exception.AuthorizationException;
import java.util.Arrays;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class CookieExtractor implements TokenExtractor {
	private static final String REFRESH_KEY = "refreshToken";

	@Override
	public String extract(HttpServletRequest request) {
		Cookie[] cookies = getCookies(request);
		Optional<Cookie> tokenCookie =
				Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(REFRESH_KEY)).findAny();

		Cookie refreshCookie =
				tokenCookie.orElseThrow(() -> new AuthorizationException("토큰이 존재하지 않습니다."));

		return getValue(refreshCookie);
	}

	private Cookie[] getCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			throw new AuthorizationException("토큰이 존재하지 않습니다");
		}
		return cookies;
	}

	private String getValue(Cookie cookie) {
		String token = cookie.getValue();
		validNullToken(token);
		return token;
	}

	private void validNullToken(String token) {
		if (token == null) {
			throw new AuthorizationException("토큰이 존재하지 않습니다");
		}
	}
}
