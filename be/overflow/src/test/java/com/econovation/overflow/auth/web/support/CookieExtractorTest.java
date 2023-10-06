package com.econovation.overflow.auth.web.support;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.econovation.overflow.auth.domain.exception.AuthorizationException;
import javax.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletRequest;

@Import(CookieExtractor.class)
class CookieExtractorTest {
	private static final String REFRESH_TOKEN_KEY = "refreshToken";
	private static final String JWT_TOKEN = "A".repeat(32);

	private final CookieExtractor cookieExtractor = new CookieExtractor();

	@Test
	@DisplayName("쿠키 key값이 존재할 경우 쿠키를 추출할 수 있다.")
	void extract_cookie() {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest();
		Cookie cookie = new Cookie(REFRESH_TOKEN_KEY, JWT_TOKEN);
		request.setCookies(cookie);

		// when
		String value = cookieExtractor.extract(request);

		// then
		assertThat(value).isEqualTo(JWT_TOKEN);
	}

	@Test
	@DisplayName("쿠키 key에 value가 없을 경우 예외가 발생한다.")
	void extract_cookie_exception() {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest();
		Cookie cookie = new Cookie(REFRESH_TOKEN_KEY, null);
		request.setCookies(cookie);

		// when & then
		assertThatThrownBy(() -> cookieExtractor.extract(request))
				.isInstanceOf(AuthorizationException.class);
	}

	@Test
	@DisplayName("request에 cookie가 존재하지 않을 경우 예외가 발생한다.")
	void extract_cookie_not_found() {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest();

		// when & then
		assertThatThrownBy(() -> cookieExtractor.extract(request))
				.isInstanceOf(AuthorizationException.class);
	}
}
