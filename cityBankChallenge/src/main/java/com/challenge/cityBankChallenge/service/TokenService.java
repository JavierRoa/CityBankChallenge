package com.challenge.cityBankChallenge.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.challenge.cityBankChallenge.model.User;

@Service
public class TokenService {
	
	@Value("${cityBankChallenge.security.secret}")
	
	private String apiSecret;

	public String tokenGeneration(User user) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    return JWT.create()
		        .withIssuer("CityBank")
		        .withSubject(user.getLogin())
		        .withClaim("id", user.getId())
		        .withExpiresAt(expirationTime())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException();
		}
	}
	
	public String getSubject(String token) {
		
		if(token == null) {
			throw new RuntimeException("El token no es válido");
		}
		
		DecodedJWT verifier = null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    verifier = JWT.require(algorithm)
		        .withIssuer("CityBank")
		        .build()
		        .verify(token);
		    verifier.getSubject();
		} catch (JWTVerificationException exception){
		    System.out.println(exception.toString());
		}
		if(verifier.getSubject() == null) {
			throw new RuntimeException("Invalid Verifier");
		}
		return verifier.getSubject();
	}
	
	private Instant expirationTime() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));	
	}
}
