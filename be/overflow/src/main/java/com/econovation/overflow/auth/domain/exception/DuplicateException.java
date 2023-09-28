package com.econovation.overflow.auth.domain.exception;

import com.econovation.overflow.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicateException extends BusinessException {
	public DuplicateException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
