package com.econovation.overflow.auth.domain.model.converter;

import static org.mockito.Mockito.verify;

import com.econovation.overflow.auth.domain.dto.request.SaveUserRequest;
import com.econovation.overflow.auth.domain.helper.Encoder;
import com.econovation.overflow.auth.domain.model.UserModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserModelConverterTest {

	@Mock private Encoder encoder;
	@InjectMocks private UserModelConverter userModelConverter;
	private static final String PASSWORD = "password";

	@Test
	@DisplayName("request를 model로 변환한다.")
	void request_convert_model() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().build();

		// when
		UserModel model = userModelConverter.from(request);

		// then
		Assertions.assertThat(model).isInstanceOf(UserModel.class);
	}

	@Test
	@DisplayName("request를 model로 변환할 때 암호화를 진행한다.")
	void encode_convert_model() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().password(PASSWORD).build();

		// when
		UserModel model = userModelConverter.from(request);

		// then
		verify(encoder).encode(PASSWORD);
	}
}
