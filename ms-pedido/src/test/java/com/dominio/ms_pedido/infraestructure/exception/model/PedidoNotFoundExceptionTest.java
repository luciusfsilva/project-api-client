package com.dominio.ms_pedido.infraestructure.exception.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.dominio.ms_pedido.infrastructure.exception.model.PedidoNotFoundException;

public class PedidoNotFoundExceptionTest {
	
	@Test
	public void testExceptionMessage() {
		String mensagem = "Pedido não encontrado";
		PedidoNotFoundException exception = new PedidoNotFoundException(mensagem);
		
		assertNotNull(exception);
		assertEquals(mensagem, exception.getMessage());
	}

}
