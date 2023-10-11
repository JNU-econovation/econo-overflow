package com.econovation.overflow.auth.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.econovation.overflow.auth.domain.dto.request.LoginUserRequest;
import com.econovation.overflow.auth.domain.dto.response.TokenResponse;
import com.econovation.overflow.auth.domain.exception.NotFoundEmailException;
import com.econovation.overflow.auth.domain.exception.NotFoundPasswordException;
import com.econovation.overflow.auth.domain.helper.Encoder;
import com.econovation.overflow.auth.domain.service.CreateTokenService;
import com.econovation.overflow.auth.persistence.entity.UserEntity;
import com.econovation.overflow.auth.persistence.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
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
		when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

		// when & then
		assertThrows(NotFoundEmailException.class, () -> loginUserUseCase.execute(request));
	}

	@Test
	@DisplayName("로그인 시 비밀번호가 맞지 않으면 예외가 발생한다.")
	void same_password_check_when_login() {
		// given
		LoginUserRequest request = LoginUserRequest.builder().build();
		UserEntity entity = UserEntity.builder().build();
		when(userRepository.findByEmail(any())).thenReturn(Optional.of(entity));
		when(encoder.matches(any(), any())).thenReturn(Boolean.FALSE);

		// when & then
		assertThrows(NotFoundPasswordException.class, () -> loginUserUseCase.execute(request));
	}

	@Test
	@DisplayName("로그인을 성공하면 토큰 정보를 전달한다.")
	void pass_token_info_when_success_login() {
		// given
		LoginUserRequest request = LoginUserRequest.builder().email(any()).build();
		UserEntity entity = UserEntity.builder().build();
		TokenResponse expected = TokenResponse.builder().build();

		when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(entity));
		when(encoder.matches(any(), any())).thenReturn(Boolean.TRUE);
		when(createTokenService.execute(any())).thenReturn(expected);

		// when
		TokenResponse actual = loginUserUseCase.execute(request);

		// then
		Assertions.assertEquals(expected, actual);
	}
}
