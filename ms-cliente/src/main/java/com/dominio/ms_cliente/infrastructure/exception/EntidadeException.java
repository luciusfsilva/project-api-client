package com.dominio.ms_cliente.infrastructure.exception;

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
