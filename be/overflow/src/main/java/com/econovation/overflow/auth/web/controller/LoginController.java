package com.econovation.overflow.auth.web.controller;

import com.econovation.overflow.auth.domain.usecase.LoginUserUseCase;
import com.econovation.overflow.auth.web.dto.request.LoginUserRequest;
import com.econovation.overflow.auth.web.dto.response.TokenResponse;
import com.econovation.overflow.common.support.respnose.ApiResponse;
import com.econovation.overflow.common.support.respnose.ApiResponseBody.SuccessBody;
import com.econovation.overflow.common.support.respnose.ApiResponseGenerator;
import com.econovation.overflow.common.support.respnose.MessageCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {
	private static final String REFRESH_TOKEN = "refreshToken";
	private static final int REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60;
	private final LoginUserUseCase loginUserUseCase;

	@PostMapping("/login")
	public ApiResponse<SuccessBody<TokenResponse>> signIn(
			@RequestBody @Valid LoginUserRequest request) {
		final TokenResponse tokenResponse = loginUserUseCase.execute(request);
		final ResponseCookie cookie = putTokenInCookie(tokenResponse);
		return ApiResponseGenerator.success(
				tokenResponse, HttpStatus.OK, MessageCode.CREATE, cookie.toString());
	}

	private ResponseCookie putTokenInCookie(final TokenResponse tokenResponse) {
		return ResponseCookie.from(REFRESH_TOKEN, tokenResponse.getRefreshToken())
				.maxAge(REFRESH_TOKEN_EXPIRATION)
				.path("/")
				.sameSite("None")
				.secure(true)
				.httpOnly(true)
				.build();
	}
}
