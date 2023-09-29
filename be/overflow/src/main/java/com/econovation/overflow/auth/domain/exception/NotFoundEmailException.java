package com.econovation.overflow.auth.domain.exception;

import com.econovation.overflow.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundEmailException extends BusinessException {

	public NotFoundEmailException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
