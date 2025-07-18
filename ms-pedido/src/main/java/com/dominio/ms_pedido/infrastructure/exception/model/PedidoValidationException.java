package com.dominio.ms_pedido.infrastructure.exception.model;

public class PedidoValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoValidationException(String message) {
		super(message);
	}

}
