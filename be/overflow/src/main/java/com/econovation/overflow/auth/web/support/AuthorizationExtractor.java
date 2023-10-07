package com.econovation.overflow.auth.web.support;

import com.econovation.overflow.auth.domain.exception.AuthorizationException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public class AuthorizationExtractor {
	private static final String BEARER_TYPE = "Bearer";

	public static String extract(final HttpServletRequest request) {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (Objects.isNull(authorization)) {
			throw new AuthorizationException("인증 토큰이 존재하지 않습니다");
		}
		validateAuthorizationFormat(authorization);
		return authorization.substring(BEARER_TYPE.length());
	}

	private static void validateAuthorizationFormat(String authorization) {
		if (authorization.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
			return;
		}
		throw new AuthorizationException("token 형식이 알맞지 않습니다");
	}
}
