package com.dominio.ms_pagamento.domain;

import org.junit.jupiter.api.Test;

import com.dominio.ms_pagamento.domain.model.PagamentoLog;

public class PagamentoLogTest {
	
	@Test
	public void prePersistTest() {
		PagamentoLog pagamentoLog = new PagamentoLog();
		
		pagamentoLog.prePersist();
		
		assert pagamentoLog.getDataHoraTransacao() != null;
	}

}
