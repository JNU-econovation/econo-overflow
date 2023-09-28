package com.econovation.overflow.auth.web.controller;

import com.econovation.overflow.auth.domain.usecase.ValidEmailUseCase;
import com.econovation.overflow.auth.domain.usecase.ValidNicknameUseCase;
import com.econovation.overflow.auth.web.dto.request.ValidEmailRequest;
import com.econovation.overflow.auth.web.dto.request.ValidNicknameRequest;
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
public class ValidController {
	private final ValidEmailUseCase validEmailUseCase;
	private final ValidNicknameUseCase validNicknameUseCase;

	@PostMapping("/email-check")
	public ApiResponse<SuccessBody<Void>> validEmail(@RequestBody @Valid ValidEmailRequest request) {
		validEmailUseCase.execute(request);
		return ApiResponseGenerator.success(HttpStatus.CREATED, MessageCode.CREATE);
	}

	@PostMapping("/nickname-check")
	public ApiResponse<SuccessBody<Void>> validNickname(
			@RequestBody @Valid ValidNicknameRequest request) {
		validNicknameUseCase.execute(request);
		return ApiResponseGenerator.success(HttpStatus.CREATED, MessageCode.CREATE);
	}
}
