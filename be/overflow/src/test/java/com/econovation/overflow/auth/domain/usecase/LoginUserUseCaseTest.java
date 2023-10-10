package com.econovation.overflow.auth.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.econovation.overflow.auth.domain.dto.request.LoginUserRequest;
import com.econovation.overflow.auth.domain.exception.NotFoundEmailException;
import com.econovation.overflow.auth.domain.helper.Encoder;
import com.econovation.overflow.auth.domain.service.CreateTokenService;
import com.econovation.overflow.auth.persistence.entity.UserEntity;
import com.econovation.overflow.auth.persistence.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginUserUseCaseTest {
	@Mock private UserRepository userRepository;

	@Mock private CreateTokenService createTokenService;

	@InjectMocks private LoginUserUseCase loginUserUseCase;

	@Mock private Encoder encoder;

	@Test
	@DisplayName("로그인 시 존재하지 않는 이메일이면 예외가 발생한다.")
	void exist_email_check_when_login() {
		// given
		LoginUserRequest request = LoginUserRequest.builder().build();
		UserEntity entity = UserEntity.builder().build();

		when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

		// when & then
		assertThrows(NotFoundEmailException.class, () -> loginUserUseCase.execute(request));
	}
}
