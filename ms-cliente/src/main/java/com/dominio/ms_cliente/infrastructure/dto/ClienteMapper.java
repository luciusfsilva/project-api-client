package com.dominio.ms_cliente.infrastructure.dto;

import org.mapstruct.Mapper;

import com.dominio.ms_cliente.domain.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	ClienteDTO toDTO(Cliente cliente);
	Cliente toModel(ClienteDTO clienteDTO);

}
