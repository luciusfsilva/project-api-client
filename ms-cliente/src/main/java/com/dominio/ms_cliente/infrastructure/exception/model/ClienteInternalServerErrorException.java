package com.dominio.ms_cliente.infrastructure.exception.model;

public class ClienteInternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteInternalServerErrorException(String message) {
		super(message);
	}
}
