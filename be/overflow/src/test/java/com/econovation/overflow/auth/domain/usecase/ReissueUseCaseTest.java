package com.econovation.overflow.auth.domain.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.econovation.overflow.auth.domain.dto.response.TokenResponse;
import com.econovation.overflow.auth.domain.service.CreateTokenService;
import com.econovation.overflow.auth.domain.service.GetAuthInfoService;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReissueUseCaseTest {
	@InjectMocks private ReissueUseCase reissueUseCase;

	@Mock private AuthInfoRepository authInfoRepository;
	@Mock private GetAuthInfoService getAuthInfoService;
	@Mock private CreateTokenService createTokenService;

	@Test
	@DisplayName("기존에 존재하는 토큰을 삭제한다.")
	void delete_token_when_reissue() {
		// given
		AuthInfoEntity authInfoEntity = AuthInfoEntity.builder().build();
		TokenResponse tokenResponse = TokenResponse.builder().build();

		when(getAuthInfoService.execute(any())).thenReturn(authInfoEntity);
		when(createTokenService.execute(any())).thenReturn(tokenResponse);

		// when
		reissueUseCase.execute(any());

		// then
		verify(authInfoRepository).delete(authInfoEntity);
	}

	@Test
	@DisplayName("재발급 요청 메시지를 전달한다.")
	void crate_token_when_reissue() {
		// given
		AuthInfoEntity authInfoEntity = AuthInfoEntity.builder().build();

		when(getAuthInfoService.execute(any())).thenReturn(authInfoEntity);
		doNothing().when(authInfoRepository).delete(authInfoEntity);

		// when
		reissueUseCase.execute(any());

		// then
		verify(createTokenService).execute(any());
	}
}
