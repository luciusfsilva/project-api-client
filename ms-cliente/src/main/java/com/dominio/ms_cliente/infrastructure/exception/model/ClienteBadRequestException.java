package com.dominio.ms_cliente.infrastructure.exception.model;

public class ClienteBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteBadRequestException(String message) {
		super(message);
	}
}
