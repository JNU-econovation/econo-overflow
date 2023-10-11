package com.econovation.overflow.auth.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.econovation.overflow.auth.domain.exception.AuthorizationException;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import com.econovation.overflow.support.token.TokenResolver;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAuthInfoServiceTest {
	@Mock private TokenResolver tokenResolver;

	@Mock private AuthInfoRepository authInfoRepository;

	@InjectMocks private GetAuthInfoService getAuthInfoService;

	@Test
	@DisplayName("token으로부터 auth 정보를 가져온다.")
	void get_auth_info_by_token() {
		// given
		Long userId = 1L;
		String token = "token";

		AuthInfoEntity actual = AuthInfoEntity.builder().build();
		when(tokenResolver.getUserInfo(token)).thenReturn(userId);
		when(authInfoRepository.findByUserIdAndToken(userId, token))
				.thenReturn(Optional.ofNullable(actual));

		// then
		AuthInfoEntity expected = getAuthInfoService.execute(token);

		// then
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("token과 유저 정보에 해당하는 auth정보가 없다면 예외가 발생한다.")
	void exception_when_get_auth_info_by_token() {
		// given
		Long userId = 1L;
		String token = "token";

		when(tokenResolver.getUserInfo(token)).thenReturn(userId);
		when(authInfoRepository.findByUserIdAndToken(userId, token))
				.thenThrow(AuthorizationException.class);

		// then & then
		assertThrows(AuthorizationException.class, () -> getAuthInfoService.execute(token));
	}
}
