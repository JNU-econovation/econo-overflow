package com.econovation.overflow.auth.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;

import com.econovation.overflow.auth.domain.dto.request.SaveUserRequest;
import com.econovation.overflow.auth.domain.exception.DuplicateException;
import com.econovation.overflow.auth.persistence.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidNicknameUseCaseTest {
	@Mock private UserRepository userRepository;

	@InjectMocks private ValidNicknameUseCase validNicknameUseCase;

	@Test
	@DisplayName("닉네임 중복되면 예외가 발생한다.")
	void duplicated_email() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().nickname("nickname").build();

		Mockito.when(userRepository.existsByNickname(request.getNickname())).thenReturn(Boolean.TRUE);

		// when & then
		assertThrows(DuplicateException.class, () -> validNicknameUseCase.execute(request));
	}

	@Test
	@DisplayName("닉네임 중복이 되지 않으면 예외가 발생하지 않는다.")
	void not_duplicated_email() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().nickname("nickname").build();

		Mockito.when(userRepository.existsByNickname(request.getNickname())).thenReturn(Boolean.FALSE);

		// when & then
		assertDoesNotThrow(() -> validNicknameUseCase.execute(request));
	}
}
