package com.econovation.overflow.auth.web.dto.converter;

import com.econovation.overflow.auth.web.dto.response.TokenResponse;
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
