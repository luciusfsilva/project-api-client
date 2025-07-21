package com.dominio.ms_pagamento.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dominio.ms_pagamento.domain.enums.TransacaoEnum;
import com.dominio.ms_pagamento.domain.model.Pagamento;
import com.dominio.ms_pagamento.domain.service.PagamentoLogService;
import com.dominio.ms_pagamento.domain.service.PagamentoService;
import com.dominio.ms_pagamento.infrastructure.log.TransacaoLog;
import com.dominio.ms_pagamento.infrastructure.repository.PagamentoRepository;

@Service
public class PagamentoServiceImpl extends BaseModelServiceImpl<Pagamento> implements PagamentoService {
	
	private final PagamentoLogService pagamentoLogService;
	
	public PagamentoServiceImpl(PagamentoLogService pagamentoLogService, 
			PagamentoRepository pagamentoRepository) {
		super(pagamentoRepository);
		this.pagamentoLogService = pagamentoLogService;
	}	

	@Override
	@TransacaoLog(transacao = TransacaoEnum.SAVE)
	public Pagamento save(Pagamento t) {
		pagamentoLogService.saveLog(TransacaoEnum.SAVE);
		return super.save(t);
	}

	@Override
	@TransacaoLog(transacao = TransacaoEnum.FIND_BY_ID)
	public Pagamento findById(Long id) {
		pagamentoLogService.saveLog(TransacaoEnum.FIND_BY_ID);
		return super.findById(id);
	}

	@Override
	@TransacaoLog(transacao = TransacaoEnum.FIND_ALL)
	public List<Pagamento> findAll() {
		pagamentoLogService.saveLog(TransacaoEnum.FIND_ALL);
		return super.findAll();
	}

}
