package com.dominio.ms_cliente.infrastructure.exception.model;

public class ClienteValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteValidationException(String message) {
		super(message);
	}
}
