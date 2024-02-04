package com.example.library.configuration;

import javax.crypto.SecretKey;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class SecurityConstants {

	public static final long JWT_EXPIRATION = 60000; // Millisecondi 1000 = 1 Secondo
	public static final SecretKey JWT_SECRET = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode("9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9"));
}
