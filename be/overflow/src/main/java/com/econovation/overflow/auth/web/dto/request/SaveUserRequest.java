package com.econovation.overflow.auth.web.dto.request;

import com.econovation.overflow.auth.web.supplier.EmailSupplier;
import com.econovation.overflow.auth.web.supplier.NicknameSupplier;
import com.econovation.overflow.common.annotation.SpecialLetter;
import com.econovation.overflow.common.annotation.UpperCase;
import com.econovation.overflow.common.marker.AbstractRequestDto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SaveUserRequest implements AbstractRequestDto, EmailSupplier, NicknameSupplier {

	@NotNull private String nickname;
	@NotNull @Email private String email;

	@NotNull
	@Size(min = 8)
	@SpecialLetter
	@UpperCase
	private String password;
}
