package com.example.library.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.library.configuration.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenGenerator {

	public String generateToken(Authentication authentication) {
		
		String username = authentication.getName();
		Date expireDate = new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION);
	
		String token = Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(expireDate)
				.signWith(SecurityConstants.JWT_SECRET, Jwts.SIG.HS256)
				.compact();
		
		return token;
	}
	
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser()
				.verifyWith(SecurityConstants.JWT_SECRET)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
		return claims.getSubject();
	}
	
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(SecurityConstants.JWT_SECRET).build().parseSignedClaims(token);
			return true;
		} catch (JwtException e) {
			throw new JwtException("JWT was expired or incorrect");
		}
	}
}
