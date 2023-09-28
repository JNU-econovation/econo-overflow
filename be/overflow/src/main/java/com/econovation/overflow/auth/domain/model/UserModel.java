package com.econovation.overflow.auth.domain.model;

import com.econovation.overflow.auth.web.supplier.EmailSupplier;
import com.econovation.overflow.auth.web.supplier.NicknameSupplier;
import com.econovation.overflow.common.marker.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserModel implements AbstractModel, NicknameSupplier, EmailSupplier {
	private String nickname;
	private String email;
	private String password;
}
