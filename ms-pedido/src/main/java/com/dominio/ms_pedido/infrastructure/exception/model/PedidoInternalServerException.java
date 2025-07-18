package com.dominio.ms_pedido.infrastructure.exception.model;

public class PedidoInternalServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoInternalServerException(String message) {
		super(message);
	}

}
