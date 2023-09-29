package com.econovation.overflow.security.authority;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@Getter
@ToString
public enum UserRole {
	USER("ROLE_USER"){
		@Override
		public GrantedAuthority getAuthority(){
			return UserAuthority.builder().build();
		}
	}
	;

	private final String role;

	UserRole(String role) {
		this.role = role;
	}

	public abstract GrantedAuthority getAuthority();

	public static UserRole roleOf(String role) {
		for (UserRole userRole : UserRole.values()) {
			if (userRole.getRole().equals(role)) {
				return userRole;
			}
		}
		throw new IllegalArgumentException("Invalid role. role: " + role);
	}
}
