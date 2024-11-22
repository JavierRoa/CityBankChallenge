package com.challenge.cityBankChallenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity(name = "Book")
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "ISBN is required")
    @Column(unique = true)
    private String isbn;
    
    @NotNull
    @Min(value = 0, message = "Publication year must be positive")
    @Max(value = 2024, message = "Publication year can't be in the future")
    private Integer publicationYear;

    @NotNull(message = "Quantity must be specified")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;
}