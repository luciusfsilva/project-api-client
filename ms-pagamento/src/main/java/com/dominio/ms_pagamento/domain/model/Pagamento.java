package com.dominio.ms_pagamento.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.dominio.ms_pagamento.domain.enums.StatusPagamentoEnum;

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

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamento")
public class Pagamento extends BaseModel {
	
	@Column(name = "id_pedido", nullable = false, unique = true)
	private UUID idPedido;
	
	@Enumerated(EnumType.STRING)
	private StatusPagamentoEnum statusPagamento;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
	@Column(name = "data_pagamento", nullable = false)
	private LocalDateTime dataPagamento;

	@PrePersist
	public void prePersist() {
		if (this.dataPagamento == null) {
			this.dataPagamento = LocalDateTime.now();
		}
	}
}
