package com.econovation.overflow.auth.infra.converter;

import com.econovation.overflow.auth.domain.model.UserModel;
import com.econovation.overflow.auth.infra.entity.UserEntity;
import com.econovation.overflow.common.support.converter.AbstractEntityConverter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter implements AbstractEntityConverter<UserEntity, UserModel> {

	@Override
	public UserModel from(UserEntity entity) {
		return UserModel.builder()
				.nickname(entity.getNickname())
				.email(entity.getEmail())
				.password(entity.getPassword())
				.build();
	}

	@Override
	public UserEntity toEntity(UserModel model) {
		return UserEntity.builder()
				.nickname(model.getNickname())
				.email(model.getEmail())
				.password(model.getPassword())
				.build();
	}
}
