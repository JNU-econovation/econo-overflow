package com.econovation.overflow.auth.domain.model.converter;

import com.econovation.overflow.auth.domain.dto.request.SaveUserRequest;
import com.econovation.overflow.auth.domain.model.UserModel;
import com.econovation.overflow.common.support.converter.AbstractDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserModelConverter implements AbstractDtoConverter<SaveUserRequest, UserModel> {
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserModel from(SaveUserRequest request) {
		return UserModel.builder()
				.nickname(request.getNickname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.build();
	}
}
