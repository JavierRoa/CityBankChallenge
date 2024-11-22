package com.challenge.cityBankChallenge.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.challenge.cityBankChallenge.dto.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ExcHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity handleError404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleError400(MethodArgumentNotValidException exception) {
		return ResponseEntity.badRequest().body(exception.getFieldErrors().stream().map(ExceptionDTO::new).toList());
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException exception) {
        // Opcional: Logear la excepción para diagnóstico
        exception.printStackTrace();

        // Respuesta personalizada para violaciones de integridad
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Se ha producido un conflicto con los datos ingresados. Verifica que los valores no sean duplicados o inválidos.");
    }
}
