package com.econovation.overflow.auth.domain.usecase;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.econovation.overflow.auth.domain.dto.request.SaveUserRequest;
import com.econovation.overflow.auth.domain.model.UserModel;
import com.econovation.overflow.auth.domain.model.converter.UserModelConverter;
import com.econovation.overflow.auth.persistence.converter.UserEntityConverter;
import com.econovation.overflow.auth.persistence.entity.UserEntity;
import com.econovation.overflow.auth.persistence.repository.UserRepository;
import com.econovation.overflow.security.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

@ExtendWith(MockitoExtension.class)
@Import(SecurityConfig.class)
class SaveUserUseCaseTest {
	@Mock private ValidEmailUseCase validEmailUseCase;

	@Mock private ValidNicknameUseCase validNicknameUseCase;

	@Mock private UserModelConverter modelConverter;
	@Mock private UserEntityConverter entityConverter;
	@Mock private UserRepository userRepository;

	@InjectMocks private SaveUserUseCase saveUserUseCase;

	@Test
	@DisplayName("회원가입 시 이메일 중복체크를 한다.")
	void email_duplicated_check_when_signup() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().build();
		UserModel userModel = UserModel.builder().build();

		when(modelConverter.from(request)).thenReturn(userModel);

		// when
		saveUserUseCase.execute(request);

		// then
		verify(validEmailUseCase).execute(userModel);
	}

	@Test
	@DisplayName("회원가입 시 닉네임 중복체크를 한다.")
	void nickname_duplicated_check_when_signup() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().build();
		UserModel model = UserModel.builder().build();

		when(modelConverter.from(request)).thenReturn(model);

		// when
		saveUserUseCase.execute(request);

		// then
		verify(validNicknameUseCase).execute(model);
	}

	@Test
	@DisplayName("회원가입 시 repository에게 save 메시지를 전달한다.")
	void save_message_when_signup() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().build();
		UserModel model = UserModel.builder().build();
		UserEntity entity = UserEntity.builder().build();

		when(modelConverter.from(request)).thenReturn(model);
		when(entityConverter.toEntity(model)).thenReturn(entity);

		// when
		saveUserUseCase.execute(request);

		// then
		verify(userRepository).save(entity);
	}
}
