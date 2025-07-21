package com.dominio.ms_pagamento.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dominio.ms_pagamento.domain.enums.TransacaoEnum;
import com.dominio.ms_pagamento.domain.model.PagamentoLog;
import com.dominio.ms_pagamento.domain.service.PagamentoLogService;
import com.dominio.ms_pagamento.infrastructure.repository.PagamentoLogRepository;

public class PagamentoLogServiceImplTest {

	@Mock
	private PagamentoLogService pagamentoLogService;

	@InjectMocks
	private PagamentoLogServiceImpl pagamentoLogServiceImpl;

	@Mock
	private PagamentoLogRepository pagamentoLogRepository;

	PagamentoLog pagamentoLog = new PagamentoLog();

	@BeforeEach
	public void setup() {
		// Initialize mocks and other setup code if necessary
		MockitoAnnotations.openMocks(this);
		pagamentoLog = PagamentoLog.builder().transacao(TransacaoEnum.SAVE).dataHoraTransacao(LocalDateTime.now())
				.build();
	}

	@Test
	public void testSalvarLog() {
		pagamentoLogServiceImpl.saveLog(TransacaoEnum.SAVE);
		when(pagamentoLogRepository.save(pagamentoLog)).thenReturn(pagamentoLog);
		verify(pagamentoLogRepository, times(1)).save(any(PagamentoLog.class));
	}

	@Test
	public void testeSaveLogException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			pagamentoLogServiceImpl.saveLog(null); // transação nula
		});
		String expectedMessage = "Transação não pode ser nula";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
}
