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

	private static final String USER_ID_CLAIM_KEY = "memberId";
	private static final String USER_ROLE_CLAIM_KEY = "memberRole";
	private final SecretKey accessSecretKey;

	private final long accessValidTime;
	private final SecretKey refreshSecretKey;

	private final long refreshValidTime;

	public TokenProvider(
			@Value("${security.jwt.token.access.secretKey}")
			String accessSecretKey,
			@Value("${security.jwt.token.access.validTime}")
			long accessValidTime,
			@Value("${security.jwt.token.refresh.secretKey}")
			String refreshSecretKey,
			@Value("${security.jwt.token.refresh.validTime}")
			long refreshValidTime
	) {
		this.accessSecretKey = Keys.hmacShaKeyFor(
				accessSecretKey.getBytes(StandardCharsets.UTF_8));
		this.accessValidTime = accessValidTime;
		this.refreshSecretKey = Keys.hmacShaKeyFor(
				refreshSecretKey.getBytes(StandardCharsets.UTF_8));
		this.refreshValidTime = refreshValidTime;
	}

	private String createToken(final Long userId, final List<UserRole> userRoles, final SecretKey secretKey,
			final long validTime) {
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

	public String createAccessToken(final Long userId, final List<UserRole> userRoles) {
		return createToken(userId, userRoles, accessSecretKey, accessValidTime);
	}

	public String createRefreshToken(final Long userId, final List<UserRole> userRoles) {
		return createToken(userId, userRoles, refreshSecretKey, refreshValidTime);
	}

}
