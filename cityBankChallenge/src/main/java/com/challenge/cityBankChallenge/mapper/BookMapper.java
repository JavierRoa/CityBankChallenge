package com.challenge.cityBankChallenge.mapper;

import com.challenge.cityBankChallenge.model.Book;
import com.challenge.cityBankChallenge.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        return book == null ? null : new BookDTO(
        	book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn(),
            book.getPublicationYear(),
            book.getQuantity()
        );
    }

    public Book toEntity(BookDTO bookDTO) {
        return bookDTO == null ? null : new Book( 
            bookDTO.id(), 
            bookDTO.title(),
            bookDTO.author(),
            bookDTO.isbn(),
            bookDTO.publicationYear(),
            bookDTO.quantity()
        );
    }

    public void updateEntityFromDTO(BookDTO bookDTO, Book book) {
        if (bookDTO == null || book == null) {
            return; // No hacer nada si alguno de los objetos es nulo.
        }
        // Actualizar solo los campos necesarios
        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setIsbn(bookDTO.isbn());
        book.setPublicationYear(bookDTO.publicationYear());
        book.setQuantity(bookDTO.quantity());
    }
}