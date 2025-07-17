package com.dominio.ms_pedido.infrastructure.exception.model;

public class PedidoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoNotFoundException(String message) {
		super(message);
	}

}
