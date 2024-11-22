package com.challenge.cityBankChallenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.cityBankChallenge.dto.JwtTokenDTO;
import com.challenge.cityBankChallenge.dto.UserDTO;
import com.challenge.cityBankChallenge.model.User;
import com.challenge.cityBankChallenge.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	
	private final TokenService tokenService;
	
	public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity userAuthentication(@RequestBody @Valid UserDTO userDTO) {
		Authentication authToken = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.password());
		authenticationManager.authenticate(authToken);
		Authentication authenticatedUser = authenticationManager.authenticate(authToken);
		var jwtToken = tokenService.tokenGeneration((User) authenticatedUser.getPrincipal());
		return ResponseEntity.ok(new JwtTokenDTO(jwtToken));	
	}
}