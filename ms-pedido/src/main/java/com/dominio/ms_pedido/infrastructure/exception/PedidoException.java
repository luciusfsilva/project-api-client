package com.dominio.ms_pedido.infrastructure.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dominio.ms_pedido.infrastructure.exception.model.PedidoBadRequestException;
import com.dominio.ms_pedido.infrastructure.exception.model.PedidoInternalServerException;
import com.dominio.ms_pedido.infrastructure.exception.model.PedidoNotFoundException;
import com.dominio.ms_pedido.infrastructure.exception.model.PedidoValidationException;

@RestControllerAdvice
public class PedidoException {

	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<?> pedidoNotFoundException(Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new EntidadeException(
						HttpStatus.NOT_FOUND.value(),
						e.getMessage(),
						LocalDateTime.now()));
	}
	
	@ExceptionHandler(PedidoBadRequestException.class)
	public ResponseEntity<?> pedidoBadRequestException(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new EntidadeException(
						HttpStatus.BAD_REQUEST.value(),
						e.getMessage(),
						LocalDateTime.now()));
	}
	
	@ExceptionHandler(PedidoInternalServerException.class)
	public ResponseEntity<?> pedidoInternalServerException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new EntidadeException(
						HttpStatus.INTERNAL_SERVER_ERROR.value(),
						e.getMessage(),
						LocalDateTime.now()));
	}
	
	@ExceptionHandler(PedidoValidationException.class)
	public ResponseEntity<?> pedidoValidationException(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new EntidadeException(
						HttpStatus.BAD_REQUEST.value(),
						e.getMessage(),
						LocalDateTime.now()));
	}
}
