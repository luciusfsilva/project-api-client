package com.dominio.ms_pedido.infraestructure.exception.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.dominio.ms_pedido.infrastructure.exception.model.PedidoInternalServerException;

public class PedidoInternalServerExceptionTest {
	
	@Test
	public void testExceptionMessage() {
		String mensagem = "Erro interno no servidor";
		PedidoInternalServerException exception = new PedidoInternalServerException(mensagem);
		
		assertNotNull(exception);
		assertEquals(mensagem, exception.getMessage());
	}

}
