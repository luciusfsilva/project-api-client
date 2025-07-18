package com.dominio.ms_pedido.infrastructure.exception.model;

public class PedidoBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoBadRequestException(String message) {
		super(message);
	}

}
