package com.econovation.overflow.auth.persistence.converter;

import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthInfoEntityConverter {

	public AuthInfoEntity from(Long userId, String refreshToken) {
		return AuthInfoEntity.builder().userId(userId).token(refreshToken).build();
	}
}
