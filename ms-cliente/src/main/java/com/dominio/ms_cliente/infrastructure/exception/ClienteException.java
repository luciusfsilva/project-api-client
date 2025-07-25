package com.dominio.ms_cliente.infrastructure.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dominio.ms_cliente.infrastructure.exception.model.ClienteBadRequestException;
import com.dominio.ms_cliente.infrastructure.exception.model.ClienteInternalServerErrorException;
import com.dominio.ms_cliente.infrastructure.exception.model.ClienteNotFoundException;

@RestControllerAdvice //Retorna uma resposta HTTP personalizada para exceções lançadas em controladores REST em JSON
public class ClienteException {

	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<?> clienteNotFoundException(ClienteNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeException(
				HttpStatus.NOT_FOUND.value(), 
				ex.getMessage(), 
				LocalDateTime.now()));
	}
	
	@ExceptionHandler(ClienteBadRequestException.class)
	public ResponseEntity<?> clienteBadRequestException(ClienteBadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new EntidadeException(
						HttpStatus.BAD_REQUEST.value(), 
						ex.getMessage(), 
						LocalDateTime.now()));
	}
	
	@ExceptionHandler(ClienteInternalServerErrorException.class)
	public ResponseEntity<?> clienteInternalServerErrorException(ClienteInternalServerErrorException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EntidadeException(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				ex.getMessage(), 
				LocalDateTime.now()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> clienteValidationException(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EntidadeException(
				HttpStatus.BAD_REQUEST.value(), 
				ex.getMessage(), 
				LocalDateTime.now()));

	}
}
