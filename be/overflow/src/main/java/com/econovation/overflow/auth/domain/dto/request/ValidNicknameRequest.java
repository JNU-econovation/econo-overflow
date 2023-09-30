package com.econovation.overflow.auth.domain.dto.request;

import com.econovation.overflow.auth.web.supplier.NicknameSupplier;
import com.econovation.overflow.common.marker.AbstractRequestDto;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ValidNicknameRequest implements AbstractRequestDto, NicknameSupplier {
	@NotNull String nickname;
}
