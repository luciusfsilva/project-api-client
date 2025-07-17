package com.dominio.ms_cliente.domain.service.impl;

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

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;
import com.dominio.ms_cliente.domain.model.LogMsCliente;
import com.dominio.ms_cliente.infrastructure.repository.LogMsClienteRepository;

public class LogMsClienteServiceImpTest {
	
	@InjectMocks
	private LogMsClienteServiceImp logMsClienteServiceImp;
	
	@Mock
	private LogMsClienteRepository logMsClienteRepository;
	
	LogMsCliente logMsCliente = new LogMsCliente();
	
	@BeforeEach
	public void setUp() {
		// Initialize mocks and other setup code here
		// MockitoAnnotations.openMocks(this);
		// logMsClienteServiceImp = new LogMsClienteServiceImp(logMsClienteRepository);
		MockitoAnnotations.openMocks(this);
		
		logMsCliente = LogMsCliente.builder()
				.id(1L)
				.dataHora(LocalDateTime.now())
				.transacao("SAVE")
				.build();
	}
	
	@Test
	public void testSaveLog() {
		// Arrange
		logMsClienteServiceImp.saveLog(TransacaoEnum.SAVE);
		
		when(logMsClienteRepository.save(any(LogMsCliente.class))).thenReturn(logMsCliente);
		
		verify(logMsClienteRepository, times(1)).save(any(LogMsCliente.class));
		
	}
	
	@Test
	public void testSaveLogTransacaoNull() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			logMsClienteServiceImp.saveLog(null); // transação nula
        });

        assertEquals("Transacao cannot be null", ex.getMessage());
		
	}

}
