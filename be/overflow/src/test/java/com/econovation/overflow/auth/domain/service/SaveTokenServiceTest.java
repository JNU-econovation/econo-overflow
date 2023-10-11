package com.econovation.overflow.auth.domain.service;

import static org.mockito.Mockito.*;

import com.econovation.overflow.auth.persistence.converter.AuthInfoEntityConverter;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveTokenServiceTest {
	@Mock private AuthInfoRepository authInfoRepository;

	@InjectMocks private SaveTokenService saveTokenService;

	@Mock private AuthInfoEntityConverter converter;

	@Test
	@DisplayName("유저 id, 토큰 정보를 가진 entity를 authInfoRepository에게 save메시지를 전달한다.")
	void execute() {
		// given
		Long userId = 1L;
		String token = "token";

		AuthInfoEntity entity = AuthInfoEntity.builder().userId(userId).token(token).build();
		when(converter.from(userId, token)).thenReturn(entity);

		// when
		saveTokenService.execute(userId, token);

		// then
		verify(authInfoRepository).save(entity);
	}
}
