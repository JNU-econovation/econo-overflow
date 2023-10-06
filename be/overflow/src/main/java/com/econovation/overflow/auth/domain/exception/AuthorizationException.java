package com.econovation.overflow.auth.domain.exception;

import com.econovation.overflow.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends BusinessException {

	public AuthorizationException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
