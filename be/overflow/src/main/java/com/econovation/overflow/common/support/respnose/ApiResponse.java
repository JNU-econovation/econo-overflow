package com.econovation.overflow.common.support.respnose;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<B> extends ResponseEntity<B> {

	public ApiResponse(final HttpStatus status) {
		super(status);
	}

	public ApiResponse(final B body, final HttpStatus status) {
		super(body, status);
	}
}
