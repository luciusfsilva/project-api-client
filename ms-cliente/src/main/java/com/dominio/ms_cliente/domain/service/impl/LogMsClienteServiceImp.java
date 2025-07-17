package com.dominio.ms_cliente.domain.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;
import com.dominio.ms_cliente.domain.model.LogMsCliente;
import com.dominio.ms_cliente.domain.service.LogMsClienteService;
import com.dominio.ms_cliente.infrastructure.repository.LogMsClienteRepository;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

@Service
@Log
public class LogMsClienteServiceImp implements LogMsClienteService {

	private final LogMsClienteRepository logMsClienteRepository;

	public LogMsClienteServiceImp(LogMsClienteRepository logMsClienteRepository) {
		this.logMsClienteRepository = logMsClienteRepository;
	}

	@Override
	@Transactional
	public void saveLog(TransacaoEnum transacao) {
		if (transacao == null) {
			throw new IllegalArgumentException("Transacao cannot be null");
		}
			LogMsCliente log = LogMsCliente.builder().dataHora(LocalDateTime.now())
					.transacao(transacao.name()).build();
			logMsClienteRepository.save(log);
	}
}