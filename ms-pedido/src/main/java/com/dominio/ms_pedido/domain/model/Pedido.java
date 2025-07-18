package com.dominio.ms_pedido.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.dominio.ms_pedido.domain.enums.StatusPedidoEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(name = "cliente_id", nullable = false)
	private Long clienteId;
	
	@Column(name = "cpf_cliente", nullable = false, length = 11)
	private String cpfCliente;
	
	@Column(name = "total", nullable = false, precision = 10, scale = 2)
	private BigDecimal total;
	
	@Column(name = "data_hora", nullable = false)
	private LocalDateTime dataHora;
	
	@Enumerated
	private StatusPedidoEnum status;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemPedido> itens;
	
	@PrePersist void prePersist() {
		this.dataHora = LocalDateTime.now();
		if (this.status == null) {
			this.status = StatusPedidoEnum.PENDENTE;
		}
	}

}
