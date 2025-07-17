package com.dominio.ms_pedido.domain.service;

import com.dominio.ms_pedido.domain.enums.TransacaoEnum;

public interface LogMsPedidoService {
	
	public void saveLog(TransacaoEnum transacao);

}
