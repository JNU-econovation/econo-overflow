package com.econovation.overflow.support.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class TokenResolver {

	private static final String USER_ID_CLAIM_KEY = "userId";
	private final SecretKey secretKey;

	public TokenResolver(@Value("${security.jwt.token.secretKey}") String accessSecretKey) {
		this.secretKey = Keys.hmacShaKeyFor(accessSecretKey.getBytes(StandardCharsets.UTF_8));
	}

	private Claims getClaims(final String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			throw new NotValidToken("만료된 토큰입니다");
		}
	}

	public Date getExpiredDate(final String token) {
		Objects.requireNonNull(token);
		return getClaims(token).getExpiration();
	}

	public Long getUserInfo(final String token) {
		Objects.requireNonNull(token);

		return Long.valueOf(String.valueOf(getClaims(token).get(USER_ID_CLAIM_KEY)));
	}
}
