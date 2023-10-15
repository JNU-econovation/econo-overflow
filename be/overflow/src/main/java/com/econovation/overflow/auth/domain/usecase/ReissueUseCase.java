package com.econovation.overflow.auth.domain.usecase;

import com.econovation.overflow.auth.domain.dto.response.TokenResponse;
import com.econovation.overflow.auth.domain.service.CreateTokenService;
import com.econovation.overflow.auth.domain.service.GetAuthInfoService;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReissueUseCase {
	private final GetAuthInfoService getAuthInfoService;
	private final CreateTokenService createTokenService;
	private final AuthInfoRepository authInfoRepository;

	@Transactional
	public TokenResponse execute(final String token) {
		AuthInfoEntity authInfoEntity = getAuthInfoService.execute(token);
		authInfoRepository.delete(authInfoEntity);

		return createTokenService.execute(authInfoEntity.getUserId());
	}
}
