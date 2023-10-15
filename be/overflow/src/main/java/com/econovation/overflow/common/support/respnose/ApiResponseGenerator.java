package com.econovation.overflow.common.support.respnose;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@UtilityClass
public class ApiResponseGenerator {

	public static ApiResponse<ApiResponseBody.SuccessBody<Void>> success(
			final HttpStatus status, MessageCode code) {
		return new ApiResponse<>(
				new ApiResponseBody.SuccessBody<>(null, code.getMessage(), code.getCode()), status);
	}

	public static <D> ApiResponse<ApiResponseBody.SuccessBody<D>> success(
			final D data, final HttpStatus status, MessageCode code) {
		return new ApiResponse<>(
				new ApiResponseBody.SuccessBody<>(data, code.getMessage(), code.getCode()), status);
	}

	public static <D> ApiResponse<ApiResponseBody.SuccessBody<D>> success(
			final D data,
			final HttpStatus status,
			MessageCode code,
			String headerKey,
			String headerValue) {
		return new ApiResponse<>(
				new ApiResponseBody.SuccessBody<>(data, code.getMessage(), code.getCode()),
				ApiResponseHeader.setHeader(headerKey, headerValue),
				status);
	}

	public static <D> ApiResponse<ApiResponseBody.SuccessBody<PageResponse<D>>> success(
			final Page<D> data, final HttpStatus status, final MessageCode code) {
		return new ApiResponse<>(
				new ApiResponseBody.SuccessBody<>(
						new PageResponse<>(data), code.getMessage(), code.getCode()),
				status);
	}

	public static ApiResponse<ApiResponseBody.FailureBody> fail(
			final ApiResponseBody.FailureBody body, final HttpStatus status) {
		return new ApiResponse<>(body, status);
	}

	public static ApiResponse<ApiResponseBody.FailureBody> fail(
			final String message, final HttpStatus status) {
		return new ApiResponse<>(
				new ApiResponseBody.FailureBody(String.valueOf(status.value()), message), status);
	}

	public static ApiResponse<ApiResponseBody.FailureBody> fail(
			final String code, final String message, final HttpStatus status) {
		return new ApiResponse<>(new ApiResponseBody.FailureBody(code, message), status);
	}
}
