package com.dominio.ms_pagamento.domain.model;

import java.time.LocalDateTime;

import com.dominio.ms_pagamento.domain.enums.TransacaoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pagamento_log")
public class PagamentoLog extends BaseModel{

	@Enumerated(EnumType.STRING)
	private TransacaoEnum transacao;
	
	@Column(name = "data_hora_transacao", nullable = false)
	private LocalDateTime dataHoraTransacao;
	
	@PrePersist
	public void prePersist() {
		this.dataHoraTransacao = LocalDateTime.now();
	}
}
