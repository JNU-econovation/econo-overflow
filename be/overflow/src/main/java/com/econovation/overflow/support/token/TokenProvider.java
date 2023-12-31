package com.econovation.overflow.support.token;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

	private static final String USER_ID_CLAIM_KEY = "userId";
	private final SecretKey secretKey;

	private final long accessValidTime;
	private final long refreshValidTime;

	public TokenProvider(
			@Value("${security.jwt.token.secretKey}") String secretKey,
			@Value("${security.jwt.token.access.validTime}") long accessValidTime,
			@Value("${security.jwt.token.refresh.validTime}") long refreshValidTime) {
		this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.accessValidTime = accessValidTime;
		this.refreshValidTime = refreshValidTime;
	}

	public String createAccessToken(final Long userId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(USER_ID_CLAIM_KEY, userId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + accessValidTime))
				.signWith(secretKey)
				.compact();
	}

	public String createRefreshToken(final Long userId) {
		final Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(USER_ID_CLAIM_KEY, userId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + refreshValidTime))
				.signWith(secretKey)
				.compact();
	}
}
