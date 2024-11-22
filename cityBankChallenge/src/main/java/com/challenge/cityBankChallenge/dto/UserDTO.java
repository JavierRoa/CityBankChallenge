package com.challenge.cityBankChallenge.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
		
		@NotBlank
		String login, 
		
		@NotBlank
		String password) {

}
