package com.dominio.ms_pedido.domain.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PedidoTest {
	
	Pedido pedido = new Pedido();
	
	@BeforeEach
	public void setUp() {
		pedido = Pedido.builder()
				.clienteId(1L)
				.total(new BigDecimal("100.00"))
				.build();
	}
	
	@Test
	public void testPrePersistPedido() {
		pedido.prePersist();
		
		assertNotNull(pedido.getDataHora());
		assertNotNull(pedido.getStatus());
	}

}
