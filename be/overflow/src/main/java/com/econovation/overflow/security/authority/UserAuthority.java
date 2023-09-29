package com.econovation.overflow.security.authority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserAuthority implements GrantedAuthority {

	@Override
	public String getAuthority() {
		return UserRole.USER.getRole();
	}
}
