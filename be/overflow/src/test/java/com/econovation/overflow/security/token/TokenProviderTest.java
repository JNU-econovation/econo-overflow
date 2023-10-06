package com.econovation.overflow.security.token;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TokenProviderTest {
	private static final String JWT_SECRET_KEY = "A".repeat(32);
	private static final int ACCESS_EXPIRED_TIME = 3600;
	private static final int REFRESH_EXPIRED_TIME = 3600;
	private final TokenProvider tokenProvider =
			new TokenProvider(JWT_SECRET_KEY, ACCESS_EXPIRED_TIME, REFRESH_EXPIRED_TIME);

	@Test
	@DisplayName("엑세스 토큰을 생성한다.")
	void create_accessToken() {
		// given
		Long userId = 1L;

		// when
		String token = tokenProvider.createAccessToken(userId, Collections.emptyList());

		// then
		assertThat(token.split("\\.")).hasSize(3);
	}

	@Test
	@DisplayName("리프레시 토큰을 생성한다.")
	void create_refreshToken() {
		// given
		Long userId = 1L;

		// when
		String token = tokenProvider.createRefreshToken(userId);

		// then
		assertThat(token.split("\\.")).hasSize(3);
	}
}
