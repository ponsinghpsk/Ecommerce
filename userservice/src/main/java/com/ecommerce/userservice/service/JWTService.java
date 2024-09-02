package com.ecommerce.userservice.service;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private static final String SECRET_KEY = "VGhpcyBpcyBteSBzZWNyZXQga2V5IGZvciB1c2VyIHNlcnZpY2U=";

	public String extractUserName(String token) {
		
		String userName = Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
		
		return userName;
	}

	private SecretKey key() {
		
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String GenerateToken(String userName) {
		
		
		return Jwts.builder().signWith(key()).subject(userName).compact();
		
	}

}
