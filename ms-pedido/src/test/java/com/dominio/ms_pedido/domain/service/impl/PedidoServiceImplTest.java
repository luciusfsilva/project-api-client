package com.dominio.ms_pedido.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dominio.ms_pedido.domain.enums.StatusPedidoEnum;
import com.dominio.ms_pedido.domain.enums.TransacaoEnum;
import com.dominio.ms_pedido.domain.model.Pedido;
import com.dominio.ms_pedido.domain.service.LogMsPedidoService;
import com.dominio.ms_pedido.infrastructure.repository.PedidoRepository;

public class PedidoServiceImplTest {
	
	@InjectMocks
	private PedidoServiceImpl pedidoServiceImpl;
	
	@Mock
	private PedidoRepository pedidoRepository;
	
	@Mock
	private LogMsPedidoService LogMsPedidoService;

	Pedido pedido = new Pedido();
	
	List<Pedido> pedidos = new ArrayList<>();
	
	@BeforeEach
	public void setUP() {
		MockitoAnnotations.openMocks(this);
		
		pedido = Pedido.builder()
				.id(UUID.randomUUID())
				.clienteId(UUID.randomUUID())
				.dataHora(LocalDateTime.now())
				.total(new BigDecimal("100.00"))
				.status(StatusPedidoEnum.PENDENTE)
				.itens(List.of())
				.build();
		pedidos.add(pedido);
	}
	
	@Test
	public void testSave() {
		when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
		doNothing().when(LogMsPedidoService).saveLog(any());
		Pedido savedPedido = pedidoServiceImpl.save(pedido);
		
		assertEquals(pedido.getId(), savedPedido.getId());
		verify(LogMsPedidoService, times(1)).saveLog(TransacaoEnum.SAVE);
		verify(pedidoRepository, times(1)).save(savedPedido);
	}
	
	@Test
	public void testSaveNullPedido() {
		try {
			pedidoServiceImpl.save(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Pedido cannot be null", e.getMessage());
		}
		verify(LogMsPedidoService, times(0)).saveLog(any());
		verify(pedidoRepository, times(0)).save(any());
	}
	
	@Test
	public void testUpdate() {
		when(pedidoRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(pedido));
		when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
		doNothing().when(LogMsPedidoService).saveLog(TransacaoEnum.UPDATE);
		
		Pedido updatedPedido = pedidoServiceImpl.update(pedido.getId(), pedido);
		
		assertEquals(pedido.getId(), updatedPedido.getId());
		verify(LogMsPedidoService, times(1)).saveLog(TransacaoEnum.UPDATE);
		verify(pedidoRepository, times(1)).save(pedido);
	}
	
	@Test
	public void testUpdateNullPedido() {
		try {
			pedidoServiceImpl.update(null, null);
		} catch (IllegalArgumentException e) {
			assertEquals("Pedido and ID cannot be null", e.getMessage());
		}
		verify(LogMsPedidoService, times(0)).saveLog(any());
		verify(pedidoRepository, times(0)).save(any());
	}
	
	@Test
	public void testDelete() {
		when(pedidoRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(pedido));
		doNothing().when(LogMsPedidoService).saveLog(TransacaoEnum.DELETE);
		
		pedidoServiceImpl.delete(pedido.getId());
		
		verify(LogMsPedidoService, times(1)).saveLog(TransacaoEnum.DELETE);
		verify(pedidoRepository, times(1)).delete(pedido);
	}
	
	@Test
	public void testDeleteNullId() {
		try {
			pedidoServiceImpl.delete(null);
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be null", e.getMessage());
		}
		verify(LogMsPedidoService, times(0)).saveLog(any());
		verify(pedidoRepository, times(0)).delete(any());
	}
	
	@Test
	public void testFindById() {
		when(pedidoRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(pedido));
		doNothing().when(LogMsPedidoService).saveLog(TransacaoEnum.FIND_BY_ID);
		
		Pedido foundPedido = pedidoServiceImpl.findById(pedido.getId());
		
		assertEquals(pedido.getId(), foundPedido.getId());
		verify(LogMsPedidoService, times(1)).saveLog(TransacaoEnum.FIND_BY_ID);
		verify(pedidoRepository, times(1)).findById(pedido.getId());
	}
	
	@Test
	public void testFindByIdNull() {
		try {
			pedidoServiceImpl.findById(null);
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be null", e.getMessage());
		}
		verify(LogMsPedidoService, times(0)).saveLog(any());
		verify(pedidoRepository, times(0)).findById(any());
	}
	
	@Test
	public void testFindAll() {
		when(pedidoRepository.findAll()).thenReturn(pedidos);
		doNothing().when(LogMsPedidoService).saveLog(TransacaoEnum.FIND_ALL);
		
		List<Pedido> foundPedidos = pedidoServiceImpl.findAll();
		
		assertEquals(1, foundPedidos.size());
		assertEquals(pedido.getId(), foundPedidos.get(0).getId());
		verify(LogMsPedidoService, times(1)).saveLog(TransacaoEnum.FIND_ALL);
		verify(pedidoRepository, times(1)).findAll();
	}
}
