package com.econovation.overflow.auth.domain.dto.response;

import com.econovation.overflow.common.marker.AbstractResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TokenResponse implements AbstractResponseDto {

	private String accessToken;
	private Date expiredTime;
	@JsonIgnore private String refreshToken;
}
