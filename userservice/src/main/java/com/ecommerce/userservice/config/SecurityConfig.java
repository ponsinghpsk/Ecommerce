package com.ecommerce.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	AuthConfig authConfig;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(
				requests -> requests.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "v3/api-docs/**","/api/v1.0.0/user/**").permitAll()
						.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable())
				.addFilterBefore(authConfig, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
	

}
