package com.dominio.ms_cliente.domain.service;

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;

public interface LogMsClienteService {
	
	public void saveLog(TransacaoEnum transacao);

}
