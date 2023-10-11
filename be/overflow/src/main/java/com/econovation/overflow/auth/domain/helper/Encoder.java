package com.econovation.overflow.auth.domain.helper;

public interface Encoder {
	String encode(String raw);

	boolean matches(String raw, String hashed);
}
