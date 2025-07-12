package com.dominio.ms_cliente.domain.service.impl;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dominio.ms_cliente.domain.model.LogMsCliente;
import com.dominio.ms_cliente.domain.service.LogMsClienteService;
import com.dominio.ms_cliente.domain.service.annotations.TransacaoMsCliente;
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
	public void saveLog(String nomeMetodo) {
		Class <?> clazz = LogMsCliente.class;
		
		Method metodo;
		try {
			metodo = clazz.getDeclaredMethod(nomeMetodo);
			if (metodo.isAnnotationPresent(TransacaoMsCliente.class)) {
				TransacaoMsCliente transacao = metodo.getAnnotation(TransacaoMsCliente.class);
				LogMsCliente log = LogMsCliente.builder()
						.dataHora(LocalDateTime.now())
						.transacao(transacao.transacao().name())
						.build();
				logMsClienteRepository.save(log);
			}
		} catch (NoSuchMethodException | SecurityException e) {
			log.severe("Erro ao salvar log: " + e.getMessage());
		}
		
		
		
	}

}
