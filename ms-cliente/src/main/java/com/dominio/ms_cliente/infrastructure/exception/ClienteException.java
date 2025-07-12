package com.dominio.ms_cliente.infrastructure.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.dominio.ms_cliente.infrastructure.exception.model.ClienteBadRequestException;
import com.dominio.ms_cliente.infrastructure.exception.model.ClienteInternalServerErrorException;
import com.dominio.ms_cliente.infrastructure.exception.model.ClienteNotFoundException;

@ControllerAdvice
public class ClienteException {

	public ResponseEntity<?> clienteNotFoundException(ClienteNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EntidadeException(
				HttpStatus.NOT_FOUND.value(), 
				ex.getMessage(), 
				LocalDateTime.now()));
	}
	
	public ResponseEntity<?> clienteBadRequestException(ClienteBadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new EntidadeException(
						HttpStatus.BAD_REQUEST.value(), 
						ex.getMessage(), 
						LocalDateTime.now()));
	}
	
	public ResponseEntity<?> clienteInternalServerErrorException(ClienteInternalServerErrorException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EntidadeException(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				ex.getMessage(), 
				LocalDateTime.now()));
	}
	
	public ResponseEntity<?> clienteValidationException(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EntidadeException(
				HttpStatus.BAD_REQUEST.value(), 
				ex.getMessage(), 
				LocalDateTime.now()));

	}
}
