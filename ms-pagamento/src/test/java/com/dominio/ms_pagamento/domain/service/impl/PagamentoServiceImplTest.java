package com.dominio.ms_pagamento.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dominio.ms_pagamento.domain.enums.StatusPagamentoEnum;
import com.dominio.ms_pagamento.domain.enums.TransacaoEnum;
import com.dominio.ms_pagamento.domain.model.Pagamento;
import com.dominio.ms_pagamento.domain.service.PagamentoLogService;
import com.dominio.ms_pagamento.infrastructure.repository.PagamentoRepository;

public class PagamentoServiceImplTest {
	
	@Mock
    private PagamentoRepository pagamentoRepository;
	
	@Mock
	private PagamentoLogService pagamentoLogService;
	
	@InjectMocks
	private PagamentoServiceImpl pagamentoServiceImpl;
	
	Pagamento pagamento = new Pagamento();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		pagamento = Pagamento.builder()
				.idPedido(UUID.randomUUID())
				.statusPagamento(StatusPagamentoEnum.PENDENTE)
				.valor(new BigDecimal("100.00"))
				.dataPagamento(LocalDateTime.now())
				.build();
	}
	
	@Test
	public void testSave() {
		doNothing().when(pagamentoLogService).saveLog(TransacaoEnum.SAVE);
		when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);
		Pagamento savedPagamento = pagamentoServiceImpl.save(pagamento);
		
		assertNotNull(savedPagamento);
		verify(pagamentoRepository, times(1)).save(pagamento);
	}
	
	@Test
	public void testFindById() {
		Long id = 1L;
		doNothing().when(pagamentoLogService).saveLog(TransacaoEnum.FIND_BY_ID);
		when(pagamentoRepository.findById(id)).thenReturn(Optional.of(pagamento));
		Pagamento foundPagamento = pagamentoServiceImpl.findById(id);
		assertNotNull(foundPagamento);
		verify(pagamentoRepository, times(1)).findById(id);
	}
	
	@Test
	public void testFindAll() {
		doNothing().when(pagamentoLogService).saveLog(TransacaoEnum.FIND_ALL);
		when(pagamentoRepository.findAll()).thenReturn(List.of(pagamento));
		List<Pagamento> pagamentos = pagamentoServiceImpl.findAll();
		assertNotNull(pagamentos);
		verify(pagamentoRepository, times(1)).findAll();
	}
}