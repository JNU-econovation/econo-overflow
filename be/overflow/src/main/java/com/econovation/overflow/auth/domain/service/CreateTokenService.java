package com.econovation.overflow.auth.domain.service;

import com.econovation.overflow.auth.domain.dto.converter.TokenConverter;
import com.econovation.overflow.auth.domain.dto.response.TokenResponse;
import com.econovation.overflow.auth.persistence.converter.AuthInfoEntityConverter;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import com.econovation.overflow.security.token.TokenProvider;
import com.econovation.overflow.security.token.TokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateTokenService {
	private final TokenProvider tokenProvider;
	private final TokenConverter tokenConverter;
	private final TokenResolver tokenResolver;
	private final AuthInfoRepository authInfoRepository;
	private final AuthInfoEntityConverter authInfoEntityConverter;

	@Transactional
	public TokenResponse execute(final Long userId) {
		String accessToken = tokenProvider.createAccessToken(userId);
		String refreshToken = tokenProvider.createRefreshToken(userId);

		saveToken(userId, refreshToken);

		return tokenConverter.from(
				accessToken, tokenResolver.getExpiredDate(accessToken), refreshToken);
	}

	private void saveToken(Long userId, String token) {
		AuthInfoEntity authInfoEntity = authInfoEntityConverter.from(userId, token);
		authInfoRepository.save(authInfoEntity);
	}
}
