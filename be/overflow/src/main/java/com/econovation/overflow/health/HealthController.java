package com.econovation.overflow.health;

import com.econovation.overflow.common.support.respnose.ApiResponse;
import com.econovation.overflow.common.support.respnose.ApiResponseBody.SuccessBody;
import com.econovation.overflow.common.support.respnose.ApiResponseGenerator;
import com.econovation.overflow.common.support.respnose.MessageCode;
import java.time.LocalDateTime;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	@GetMapping("/api/health-check")
	public ApiResponse<SuccessBody<String>> check() {
		String now = LocalDateTime.now().toString();
		String cacheControl = CacheControl.noCache().getHeaderValue();

		return ApiResponseGenerator.success(
				now, HttpStatus.OK, MessageCode.SUCCESS, "Cache-Control", cacheControl);
	}
}
