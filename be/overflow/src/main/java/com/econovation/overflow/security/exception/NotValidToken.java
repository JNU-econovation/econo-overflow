package com.econovation.overflow.security.exception;

import com.econovation.overflow.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotValidToken extends BusinessException {

	public NotValidToken(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
