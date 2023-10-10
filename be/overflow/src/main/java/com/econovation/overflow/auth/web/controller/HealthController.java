package com.econovation.overflow.auth.web.controller;

import com.econovation.overflow.common.support.respnose.ApiResponse;
import com.econovation.overflow.common.support.respnose.ApiResponseBody.SuccessBody;
import com.econovation.overflow.common.support.respnose.ApiResponseGenerator;
import com.econovation.overflow.common.support.respnose.MessageCode;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	@GetMapping("/api/health-check")
	public ApiResponse<SuccessBody<String>> check() {
		return ApiResponseGenerator.success(
				String.valueOf(new Date().getTime()), HttpStatus.OK, MessageCode.SUCCESS);
	}
}
