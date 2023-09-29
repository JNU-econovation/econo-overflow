package com.econovation.overflow.auth.domain.dto.converter;

import com.econovation.overflow.auth.domain.dto.response.TokenResponse;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenConverter {

	public TokenResponse from(String accessToken, Date expiredTime, String refreshToken) {
		return TokenResponse.builder()
				.accessToken(accessToken)
				.expiredTime(expiredTime)
				.refreshToken(refreshToken)
				.build();
	}
}
