package com.example.demo.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		 http
         .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/user/**", "/h2-console/**").permitAll()
             .anyRequest().authenticated()
         )
         .headers(headers -> headers.frameOptions().disable())  // Allow H2 frames
         .formLogin(form -> form.disable());  // Optional: disable default form login

     return http.build();
	}
}
