package com.dominio.ms_pedido.infraestructure.exception.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.dominio.ms_pedido.infrastructure.exception.model.PedidoBadRequestException;

public class PedidoBadRequestExceptionTest {
	
	@Test
	void testExceptionMessage() {
		String mensagem = "Pedido inválido";
		PedidoBadRequestException exception = new PedidoBadRequestException(mensagem);

		assertNotNull(exception);
		assertEquals(mensagem, exception.getMessage());
	}

}
