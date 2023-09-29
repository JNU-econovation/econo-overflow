package com.econovation.overflow.security.token;

public class TokenValidator {}

/**
 * Date tokenExpirationDate = claims.getBody().getExpiration();
 * validateTokenExpiration(tokenExpirationDate);
 *
 * <p>private void validateTokenExpiration(Date tokenExpirationDate) { if
 * (tokenExpirationDate.before(new Date())) { throw new TokenExpirationException(); }* }
 */
