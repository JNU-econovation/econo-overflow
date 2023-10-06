package com.econovation.overflow.security.token;

import com.econovation.overflow.security.authority.UserRole;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

	private static final String USER_ID_CLAIM_KEY = "userId";
	private static final String USER_ROLE_CLAIM_KEY = "memberRole";
	private final SecretKey secretKey;

	private final long validTime;

	public TokenProvider(
			@Value("${security.jwt.token.secretKey}") String secretKey,
			@Value("${security.jwt.token.validTime}") long validTime) {
		this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.validTime = validTime;
	}

	public String createAccessToken(final Long userId, final List<UserRole> userRoles) {
		final Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(USER_ID_CLAIM_KEY, userId)
				.claim(USER_ROLE_CLAIM_KEY, userRoles.toString())
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + validTime))
				.signWith(secretKey)
				.compact();
	}

	public String createRefreshToken(final Long userId) {
		final Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(USER_ID_CLAIM_KEY, userId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + validTime))
				.signWith(secretKey)
				.compact();
	}
}
