package com.cybersoft.osahaneat.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cybersoft.osahaneat.save.JwtUtilHelper;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomJwtFilter extends OncePerRequestFilter{

	@Autowired
	JwtUtilHelper jwtUtilHelper;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token = getToken(request);
		if(token != null) {
			if(jwtUtilHelper.verifyToken(token)) {
//				System.out.print("kiem tra filter " +token);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	public String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token = null;
		if(org.springframework.util.StringUtils.hasText(header) && header.startsWith("Beaner ") ) {
			token = header.substring(7);
		}
		return token;
	}
	
	
	
	
	
}
