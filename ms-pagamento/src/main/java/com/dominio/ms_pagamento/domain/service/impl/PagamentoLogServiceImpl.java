package com.dominio.ms_pagamento.domain.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.dominio.ms_pagamento.domain.enums.TransacaoEnum;
import com.dominio.ms_pagamento.domain.model.PagamentoLog;
import com.dominio.ms_pagamento.domain.service.PagamentoLogService;
import com.dominio.ms_pagamento.infrastructure.repository.PagamentoLogRepository;

@Service
public class PagamentoLogServiceImpl extends BaseModelServiceImpl<PagamentoLog> implements PagamentoLogService {

	public PagamentoLogServiceImpl(PagamentoLogRepository pagamentoLogRepository) {
		super(pagamentoLogRepository);
	}

	@Override
	public void saveLog(TransacaoEnum transacao) {
		if (Objects.nonNull(transacao)) {
			save(new PagamentoLog(transacao, LocalDateTime.now()));
		} else {
			throw new IllegalArgumentException("Transação não pode ser nula");
		}

	}
}
