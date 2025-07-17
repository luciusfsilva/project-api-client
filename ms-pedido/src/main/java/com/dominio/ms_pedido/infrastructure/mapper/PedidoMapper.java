package com.dominio.ms_pedido.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.dominio.ms_pedido.domain.model.Pedido;
import com.dominio.ms_pedido.infrastructure.dto.PedidoDTO;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
	
	public Pedido toPedido(PedidoDTO pedidoDTO);
	
	public PedidoDTO toPedidoDTO(Pedido pedido);

}
