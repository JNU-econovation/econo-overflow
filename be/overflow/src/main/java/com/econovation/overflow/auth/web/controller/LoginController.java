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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {
	private final LoginUserUseCase loginUserUseCase;

	@PostMapping("/login")
	public ApiResponse<SuccessBody<TokenResponse>> signIn(
			@RequestBody @Valid LoginUserRequest request) {
		TokenResponse execute = loginUserUseCase.execute(request);
		return ApiResponseGenerator.success(execute, HttpStatus.OK, MessageCode.CREATE);
	}
}