package com.econovation.overflow.auth.domain.service;

import com.econovation.overflow.auth.domain.exception.AuthorizationException;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import com.econovation.overflow.support.token.TokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAuthInfoService {
	private final TokenResolver tokenResolver;
	private final AuthInfoRepository authInfoRepository;

	public AuthInfoEntity execute(String token) {
		Long userId = tokenResolver.getUserInfo(token);

		return authInfoRepository
				.findByUserIdAndToken(userId, token)
				.orElseThrow(() -> new AuthorizationException("잘못된 토큰 입니다"));
	}
}
