package com.dominio.ms_pagamento.domain;

import org.junit.jupiter.api.Test;

import com.dominio.ms_pagamento.domain.model.Pagamento;

public class PagamentoTest {
	
	@Test
	public void prePersistTest() {
		Pagamento pagamento = new Pagamento();
		pagamento.prePersist();
		
		assert pagamento.getDataPagamento() != null;
	}

}
