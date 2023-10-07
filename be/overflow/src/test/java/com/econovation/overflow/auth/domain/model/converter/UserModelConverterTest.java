package com.econovation.overflow.auth.domain.model.converter;

import com.econovation.overflow.auth.domain.dto.request.SaveUserRequest;
import com.econovation.overflow.auth.domain.model.UserModel;
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

	@Mock private PasswordEncoder passwordEncoder;
	@InjectMocks private UserModelConverter userModelConverter;
	private static final String PASSWORD = "password";

	@Test
	@DisplayName("request를 model로 변환할 때 암호화를 진행한다.")
	void encode_convert_model() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().password(PASSWORD).build();

		// when
		UserModel model = userModelConverter.from(request);

		// then
		Mockito.verify(passwordEncoder).encode(PASSWORD);
	}
}
