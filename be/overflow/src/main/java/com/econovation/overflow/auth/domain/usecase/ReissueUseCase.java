package com.econovation.overflow.auth.domain.usecase;

import com.econovation.overflow.auth.domain.dto.response.TokenResponse;
import com.econovation.overflow.auth.domain.exception.AuthorizationException;
import com.econovation.overflow.auth.domain.service.CreateTokenService;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import com.econovation.overflow.support.token.TokenResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReissueUseCase {
	private final CreateTokenService createTokenService;
	private final TokenResolver tokenResolver;
	private final AuthInfoRepository authInfoRepository;

	@Transactional
	public TokenResponse execute(final String token) {
		Long userId = tokenResolver.getUserInfo(token);

		AuthInfoEntity authInfoEntity =
				authInfoRepository
						.findByUserIdAndToken(userId, token)
						.orElseThrow(() -> new AuthorizationException("잘못된 토큰 입니다"));

		authInfoRepository.delete(authInfoEntity);
		return createTokenService.execute(userId);
	}
}
