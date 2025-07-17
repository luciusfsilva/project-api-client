package com.dominio.ms_pedido.infrastructure.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntidadeException {
	
	private Integer codigo;
	private String mensagem;
	private LocalDateTime dataHora;

}
