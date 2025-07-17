package com.dominio.ms_pedido.infraestructure.exception.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.dominio.ms_pedido.infrastructure.exception.model.PedidoValidationException;

public class PedidoValidationExceptionTest {
	
	@Test
	public void testExceptionMessage() {
		String mensagem = "Pedido inválido";
		PedidoValidationException exception = new PedidoValidationException(mensagem);

		assertNotNull(exception);
		assertEquals(mensagem, exception.getMessage());
	}

}
