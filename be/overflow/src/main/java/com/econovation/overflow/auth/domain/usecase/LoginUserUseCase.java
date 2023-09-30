package com.econovation.overflow.auth.domain.usecase;

import com.econovation.overflow.auth.domain.dto.converter.TokenConverter;
import com.econovation.overflow.auth.domain.dto.request.LoginUserRequest;
import com.econovation.overflow.auth.domain.dto.response.TokenResponse;
import com.econovation.overflow.auth.domain.exception.NotFoundEmailException;
import com.econovation.overflow.auth.domain.exception.NotFoundPasswordException;
import com.econovation.overflow.auth.domain.service.SaveTokenService;
import com.econovation.overflow.auth.persistence.entity.UserEntity;
import com.econovation.overflow.auth.persistence.repository.UserRepository;
import com.econovation.overflow.security.authority.UserRole;
import com.econovation.overflow.security.token.TokenProvider;
import com.econovation.overflow.security.token.TokenResolver;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserUseCase {

	private final TokenProvider tokenProvider;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenConverter tokenConverter;
	private final TokenResolver tokenResolver;
	private final SaveTokenService saveTokenService;

	@Transactional
	public TokenResponse execute(final LoginUserRequest request) {
		UserEntity userEntity =
				userRepository
						.findByEmail(request.getEmail())
						.orElseThrow(() -> new NotFoundEmailException("존재하지 않는 이메일입니다."));

		validPassword(request.getPassword(), userEntity);

		String accessToken =
				tokenProvider.createAccessToken(
						userEntity.getId(), Collections.singletonList(UserRole.USER));
		String refreshToken =
				tokenProvider.createRefreshToken(
						userEntity.getId(), Collections.singletonList(UserRole.USER));

		saveTokenService.execute(userEntity.getId(), refreshToken);

		return tokenConverter.from(
				accessToken, tokenResolver.getExpiredDate(accessToken), refreshToken);
	}

	private void validPassword(final String requestPassword, final UserEntity userEntity) {
		if (!matchPassword(requestPassword, userEntity.getPassword())) {
			throw new NotFoundPasswordException("존재하지 않는 비밀번호입니다");
		}
	}

	private boolean matchPassword(final String requestPassword, final String password) {
		return passwordEncoder.matches(requestPassword, password);
	}
}
