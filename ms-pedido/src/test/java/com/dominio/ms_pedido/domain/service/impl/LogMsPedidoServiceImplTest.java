package com.dominio.ms_pedido.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dominio.ms_pedido.domain.enums.TransacaoEnum;
import com.dominio.ms_pedido.domain.model.LogMsPedido;
import com.dominio.ms_pedido.infrastructure.repository.LogMsPedidoRepository;

public class LogMsPedidoServiceImplTest {
	
	@InjectMocks
	private LogMsPedidoServiceImp logMsPedidoServiceImpl;
	
	@Mock
	private LogMsPedidoRepository logMsPedidoRepository;
	
	LogMsPedido logMsPedido = new LogMsPedido();
	
	@BeforeEach
	public void setUP() {
		MockitoAnnotations.openMocks(this);
		logMsPedido = LogMsPedido.builder()
				.id(1L)
				.dataHora(LocalDateTime.now())
				.transacao(TransacaoEnum.SAVE.name())
				.build();
	}
	
	@Test
	public void savePedidoTest() {
		logMsPedidoServiceImpl.saveLog(TransacaoEnum.SAVE);
		when(logMsPedidoRepository.save(any(LogMsPedido.class))).thenReturn(logMsPedido);
		
		verify(logMsPedidoRepository).save(any(LogMsPedido.class));
	}
	
	@Test
	public void savePedidoTransacaoNullTest() {
		Exception ex = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
			logMsPedidoServiceImpl.saveLog(null); // transação nula
		});
		assertEquals("Transacao cannot be null", ex.getMessage());
	}
}
