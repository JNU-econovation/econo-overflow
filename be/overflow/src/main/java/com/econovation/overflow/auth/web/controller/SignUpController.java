package com.econovation.overflow.auth.web.controller;

import com.econovation.overflow.auth.domain.usecase.SaveUserUseCase;
import com.econovation.overflow.auth.domain.dto.request.SaveUserRequest;
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
public class SignUpController {
	private final SaveUserUseCase saveUserUseCase;

	@PostMapping("/signup")
	public ApiResponse<SuccessBody<Void>> signUp(@RequestBody @Valid SaveUserRequest request) {
		saveUserUseCase.execute(request);
		return ApiResponseGenerator.success(HttpStatus.CREATED, MessageCode.CREATE);
	}
}
