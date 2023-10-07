package com.econovation.overflow.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable();
		http.cors();

		http.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest)
				.permitAll()
				.mvcMatchers("/**")
				.permitAll();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
}
