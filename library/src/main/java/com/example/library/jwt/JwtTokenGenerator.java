package com.example.library.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.library.configuration.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenGenerator {
	
	public String generateToken(String username) {
		Date expireDate = new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION);
		
		String token = Jwts.builder()
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
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
	
	
	public boolean validateToken(String token, UserDetails userDetails) {
		Claims claims = Jwts.parser().verifyWith(SecurityConstants.JWT_SECRET).build().parseSignedClaims(token).getPayload();
		
		String username = claims.getSubject();
		
		return (username.equals(userDetails.getUsername()) && !claims.getExpiration().before(new Date()));
	
	}
}
