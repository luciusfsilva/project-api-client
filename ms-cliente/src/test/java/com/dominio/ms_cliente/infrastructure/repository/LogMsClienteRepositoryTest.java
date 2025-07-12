package com.dominio.ms_cliente.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;
import com.dominio.ms_cliente.domain.model.LogMsCliente;

@DataJpaTest
public class LogMsClienteRepositoryTest {
	
	@Autowired
	LogMsClienteRepository logMsClienteRepository;
	
	LogMsCliente log = new LogMsCliente(); 
	
	@BeforeEach
	public void setUP() {
		
		log = LogMsCliente.builder()
		.dataHora(LocalDateTime.now())
		.transacao(TransacaoEnum.SAVE.name())
		.build();
		
	}
	
	@Test
	public void saveLogTest() {
		LogMsCliente logSaved = logMsClienteRepository.save(log);
		assertTrue(logSaved.getId() != null);
		assertEquals(log.getTransacao(), logSaved.getTransacao());
	}

}
