package com.challenge.cityBankChallenge.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

public record BookDTO(
		
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    Long id,
	    
		@NotBlank(message = "Title is required")
	    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
	    String title,

	    @NotBlank(message = "Author is required")
	    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
	    String author,

	    @NotBlank(message = "ISBN is required")
	    String isbn,
	    
	    @NotNull(message = "Publication year must be specified")
	    @Min(value = 1000, message = "Publication year must be after 1000")
	    @Max(value = 2024, message = "Publication year cannot be in the future")
	    Integer publicationYear,

	    @NotNull(message = "Quantity must be specified")
	    @Min(value = 0, message = "Quantity cannot be negative")
	    @Max(value = 1000, message = "Quantity cannot exceed 1000")
	    Integer quantity) {
	
}