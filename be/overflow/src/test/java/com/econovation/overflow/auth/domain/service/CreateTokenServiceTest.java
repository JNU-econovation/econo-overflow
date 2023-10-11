package com.econovation.overflow.auth.domain.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.econovation.overflow.auth.domain.dto.converter.TokenConverter;
import com.econovation.overflow.auth.domain.dto.response.TokenResponse;
import com.econovation.overflow.auth.persistence.converter.AuthInfoEntityConverter;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import com.econovation.overflow.support.token.TokenProvider;
import com.econovation.overflow.support.token.TokenResolver;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateTokenServiceTest {
	@Mock private TokenProvider tokenProvider;
	@Mock private TokenResolver tokenResolver;
	@Mock private TokenConverter tokenConverter;
	@Mock private AuthInfoRepository authInfoRepository;
	@Mock private AuthInfoEntityConverter authInfoEntityConverter;
	@InjectMocks private CreateTokenService createTokenService;

	@Test
	@DisplayName("토큰을 저장 메시지를 전달한다.")
	void save_token() {
		// given
		Long userId = 1L;
		Date expiredDate = Date.from(Instant.EPOCH);

		String accessToken = tokenProvider.createAccessToken(userId);
		String refreshToken = tokenProvider.createRefreshToken(userId);
		TokenResponse tokenResponse = TokenResponse.builder().build();

		AuthInfoEntity authInfoEntity = authInfoEntityConverter.from(userId, refreshToken);
		when(tokenResolver.getExpiredDate(accessToken)).thenReturn(expiredDate);
		when(tokenConverter.from(accessToken, expiredDate, refreshToken)).thenReturn(tokenResponse);

		// when
		createTokenService.execute(userId);

		// then
		verify(authInfoRepository).save(authInfoEntity);
	}
}
