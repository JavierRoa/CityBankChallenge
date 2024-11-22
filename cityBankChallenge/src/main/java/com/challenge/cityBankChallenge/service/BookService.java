package com.challenge.cityBankChallenge.service;

import com.challenge.cityBankChallenge.dto.BookDTO;
import com.challenge.cityBankChallenge.mapper.BookMapper;
import com.challenge.cityBankChallenge.model.Book;
import com.challenge.cityBankChallenge.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
    	this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
    }  

    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
            .map(bookMapper::toDTO); // Mapear cada entidad a un DTO
    }

    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id)
            .map(bookMapper::toDTO);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));

        // Actualizar los campos del libro
        bookMapper.updateEntityFromDTO(bookDTO, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(updatedBook);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
}