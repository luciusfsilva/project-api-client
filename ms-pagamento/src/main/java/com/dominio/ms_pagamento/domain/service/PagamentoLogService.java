package com.dominio.ms_pagamento.domain.service;

import com.dominio.ms_pagamento.domain.enums.TransacaoEnum;

public interface PagamentoLogService {

	public void saveLog(TransacaoEnum transacao);
}
