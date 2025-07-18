package com.dominio.ms_pedido.infrastructure.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.dominio.ms_pedido.domain.enums.StatusPedidoEnum;
import com.dominio.ms_pedido.domain.model.ItemPedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	
	private UUID id;
	private Long clienteId;
	private BigDecimal total;
	private LocalDateTime dataHora;
	private StatusPedidoEnum status;
	private List<ItemPedido> itens;

}
