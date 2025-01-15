package com.cybersoft.osahaneat.security;

import java.security.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomFilterSecurity {
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	CustomJwtFilter customJwtFilter;
	
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(customUserDetailService);
		
		return authenticationManagerBuilder.build();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors().and()
			.csrf().disable().
			sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.requestMatchers("/login/**","/restaurant/files/**")
			.permitAll()
			.anyRequest()
			.authenticated()
//			.and()
//			.httpBasic()
			;
		
		http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	// Cấu hình để login không cần chứng thực, các link khác cần chứng thực
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.cors().disable()
//			.csrf().disable()
//			.authorizeHttpRequests() // authorizeRequests() khác với authorizeHttpRequests(), authorizeHttpRequests() tương đương với .anyRequest().authenticated()
//			.requestMatchers("/login/**")
//			.permitAll()
////			.authenticated();
//			.anyRequest()
//			.authenticated()
//			.and()
//			.httpBasic();
//
//		return http.build();
//					
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	
}
