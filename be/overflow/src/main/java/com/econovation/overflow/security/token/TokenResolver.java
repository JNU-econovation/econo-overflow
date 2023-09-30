package com.econovation.overflow.security.token;

import com.econovation.overflow.security.exception.NotValidToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenResolver {

	private static final String USER_ID_CLAIM_KEY = "memberId";
	private static final String USER_ROLE_CLAIM_KEY = "memberRole";
	private final SecretKey accessSecretKey;
	private final SecretKey refreshSecretKey;

	public TokenResolver(
			@Value("${security.jwt.token.access.secretKey}") String accessSecretKey,
			@Value("${security.jwt.token.refresh.secretKey}") String refreshSecretKey) {
		this.accessSecretKey = Keys.hmacShaKeyFor(accessSecretKey.getBytes(StandardCharsets.UTF_8));
		this.refreshSecretKey = Keys.hmacShaKeyFor(refreshSecretKey.getBytes(StandardCharsets.UTF_8));
	}

	private Claims getClaims(final String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(accessSecretKey)
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			throw new NotValidToken("유효하지 않은 토큰입니다.");
		}
	}

	public Date getExpiredDate(final String token) {
		return getClaims(token).getExpiration();
	}
}
