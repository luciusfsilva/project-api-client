package com.dominio.ms_pedido.domain.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dominio.ms_pedido.domain.enums.TransacaoEnum;
import com.dominio.ms_pedido.domain.model.LogMsPedido;
import com.dominio.ms_pedido.domain.service.LogMsPedidoService;
import com.dominio.ms_pedido.infrastructure.repository.LogMsPedidoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

@Service
@Log
public class LogMsPedidoServiceImp implements LogMsPedidoService {

	private final LogMsPedidoRepository logMsPedidoRepository;

	public LogMsPedidoServiceImp(LogMsPedidoRepository logMsPedidoRepository) {
		this.logMsPedidoRepository = logMsPedidoRepository;
	}

	@Override
	@Transactional
	public void saveLog(TransacaoEnum transacao) {
		if (transacao == null) {
			throw new IllegalArgumentException("Transacao cannot be null");
		}
			LogMsPedido log = LogMsPedido.builder().dataHora(LocalDateTime.now())
					.transacao(transacao.name()).build();
			logMsPedidoRepository.save(log);
	}
}