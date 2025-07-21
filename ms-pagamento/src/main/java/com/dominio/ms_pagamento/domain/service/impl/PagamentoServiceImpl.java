package com.dominio.ms_pagamento.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dominio.ms_pagamento.domain.enums.TransacaoEnum;
import com.dominio.ms_pagamento.domain.model.Pagamento;
import com.dominio.ms_pagamento.domain.service.BaseModelService;
import com.dominio.ms_pagamento.domain.service.PagamentoLogService;
import com.dominio.ms_pagamento.domain.service.PagamentoService;
import com.dominio.ms_pagamento.infrastructure.log.TransacaoLog;

@Service
public class PagamentoServiceImpl implements PagamentoService {
	
	private final BaseModelService<Pagamento> baseModelService;
	private final PagamentoLogService pagamentoLogService;
	
	public PagamentoServiceImpl(BaseModelService<Pagamento> baseModelService, PagamentoLogService pagamentoLogService) {
		this.baseModelService = baseModelService;
		this.pagamentoLogService = pagamentoLogService;
	}	

	@Override
	@TransacaoLog(transacao = TransacaoEnum.SAVE)
	public Pagamento save(Pagamento t) {
		pagamentoLogService.saveLog(TransacaoEnum.SAVE);
		return baseModelService.save(t);
	}

	@Override
	@TransacaoLog(transacao = TransacaoEnum.FIND_BY_ID)
	public Pagamento findById(Long id) {
		pagamentoLogService.saveLog(TransacaoEnum.FIND_BY_ID);
		return baseModelService.findById(id);
	}

	@Override
	@TransacaoLog(transacao = TransacaoEnum.FIND_ALL)
	public List<Pagamento> findAll() {
		pagamentoLogService.saveLog(TransacaoEnum.FIND_ALL);
		return baseModelService.findAll();
	}

}
