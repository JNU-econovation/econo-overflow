package com.econovation.overflow.auth.domain.service;

import com.econovation.overflow.auth.persistence.converter.AuthInfoEntityConverter;
import com.econovation.overflow.auth.persistence.entity.AuthInfoEntity;
import com.econovation.overflow.auth.persistence.repository.AuthInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveTokenService {
	private final AuthInfoRepository authInfoRepository;
	private final AuthInfoEntityConverter authInfoEntityConverter;

	@Transactional
	public void execute(Long userId, String token) {
		AuthInfoEntity authInfoEntity = authInfoEntityConverter.from(userId, token);
		authInfoRepository.save(authInfoEntity);
	}
}
