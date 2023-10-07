package com.econovation.overflow.auth.domain.helper;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Encoder {
	public String encode(String raw) {
		return BCrypt.hashpw(raw, BCrypt.gensalt());
	}

	public boolean matches(String raw, String hashed) {
		return BCrypt.checkpw(raw, hashed);
	}
}
