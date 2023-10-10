package com.econovation.overflow.security.token;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.econovation.overflow.security.exception.NotValidToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TokenResolverTest {
	private static final String JWT_SECRET_KEY = "A".repeat(32);
	private static final int ACCESS_EXPIRED_TIME = 3600;
	private static final int REFRESH_EXPIRED_TIME = 3600;
	private final TokenProvider tokenProvider =
			new TokenProvider(JWT_SECRET_KEY, ACCESS_EXPIRED_TIME, REFRESH_EXPIRED_TIME);
	private final TokenResolver tokenResolver = new TokenResolver(JWT_SECRET_KEY);

	@Test
	@DisplayName("토큰이 만료되지 않았을 경우 유저 정보를 가져온다")
	void get_userInfo() {
		// given
		Long userId = 1L;
		String token = tokenProvider.createAccessToken(userId);

		// when
		Long userInfo = tokenResolver.getUserInfo(token);

		// then
		assertThat(userInfo).isEqualTo(userId);
	}

	@Test
	@DisplayName("엑세스 토큰이 만료되면 예외를 터트린다.")
	void get_userInfo_by_access_exception() {
		// given
		TokenProvider expiredTokenProvider = new TokenProvider(JWT_SECRET_KEY, 0, 0);
		Long userId = 1L;
		String token = expiredTokenProvider.createAccessToken(userId);

		// when & then
		assertThatThrownBy(() -> tokenResolver.getUserInfo(token)).isInstanceOf(NotValidToken.class);
	}

	@Test
	@DisplayName("리프레시 토큰이 만료되면 예외를 터트린다.")
	void get_userInfo_by_refresh_exception() {
		// given
		TokenProvider expiredTokenProvider = new TokenProvider(JWT_SECRET_KEY, 0, 0);
		Long userId = 1L;
		String token = expiredTokenProvider.createRefreshToken(userId);

		// when & then
		assertThatThrownBy(() -> tokenResolver.getUserInfo(token)).isInstanceOf(NotValidToken.class);
	}
}
