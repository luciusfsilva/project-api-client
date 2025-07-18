package com.dominio.ms_pedido.infraestructure.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.dominio.ms_pedido.domain.enums.StatusPedidoEnum;
import com.dominio.ms_pedido.domain.model.Pedido;
import com.dominio.ms_pedido.infrastructure.dto.PedidoDTO;
import com.dominio.ms_pedido.infrastructure.mapper.PedidoMapper;
import com.dominio.ms_pedido.infrastructure.mapper.PedidoMapperImpl;

public class PedidoMapperTest {
	
	private final PedidoMapper pedidoMapper = new PedidoMapperImpl();
	
	@Test
	public void testToPedido() {
		PedidoDTO pedidoDTO = PedidoDTO.builder()
				.id(UUID.randomUUID())
				.clienteId(1L)
				.total(new BigDecimal("100.0"))
				.status(StatusPedidoEnum.PENDENTE)
				.build();
		
		Pedido pedido = pedidoMapper.toPedido(pedidoDTO);
		
		assertNotNull(pedido);
		assertNotNull(pedido.getId());
		assertNotNull(pedido.getClienteId());
		assertNotNull(pedido.getTotal());
		assertNotNull(pedido.getStatus());
	}
	
	@Test
	public void testToPedidoDTO() {
		Pedido pedido = Pedido.builder()
				.id(UUID.randomUUID())
				.clienteId(1L)
				.total(new BigDecimal("100.0"))
				.status(StatusPedidoEnum.PENDENTE)
				.build();
		
		PedidoDTO pedidoDTO = pedidoMapper.toPedidoDTO(pedido);
		
		assertNotNull(pedidoDTO);
		assertNotNull(pedidoDTO.getId());
		assertNotNull(pedidoDTO.getClienteId());
		assertNotNull(pedidoDTO.getTotal());
		assertNotNull(pedidoDTO.getStatus());
	}
}
