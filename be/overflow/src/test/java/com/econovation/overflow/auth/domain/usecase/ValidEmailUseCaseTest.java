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
class ValidEmailUseCaseTest {
	@Mock private UserRepository userRepository;

	@InjectMocks private ValidEmailUseCase validEmailUseCase;

	@Test
	@DisplayName("이메일 중복되면 예외가 발생한다.")
	void duplicated_email() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().email("email@email").build();

		Mockito.when(userRepository.existsByEmail(request.getEmail())).thenReturn(Boolean.TRUE);

		// when & then
		assertThrows(DuplicateException.class, () -> validEmailUseCase.execute(request));
	}

	@Test
	@DisplayName("이메일 중복이 되지 않으면 예외가 발생하지 않는다.")
	void not_duplicated_email() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().email("email@email").build();

		Mockito.when(userRepository.existsByEmail(request.getEmail())).thenReturn(Boolean.FALSE);

		// when & then
		assertDoesNotThrow(() -> validEmailUseCase.execute(request));
	}
}
