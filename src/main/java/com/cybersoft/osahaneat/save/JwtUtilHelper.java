package com.cybersoft.osahaneat.save;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilHelper {
	
	@Value("${jwt.privateKey}")
	private String privateKey;
	
	public String generateToken(String data) {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
		String jws = Jwts.builder().subject(data).signWith(key).compact();
		
		return jws;
		
	}
	
	public boolean verifyToken(String token) {
		try {
			SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
			Jwts.parser()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
