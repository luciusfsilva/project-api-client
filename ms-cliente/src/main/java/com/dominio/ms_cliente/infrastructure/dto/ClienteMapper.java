package com.dominio.ms_cliente.infrastructure.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dominio.ms_cliente.domain.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	ClienteDTO toDTO(Cliente cliente);
	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
	Cliente toModel(ClienteDTO clienteDTO);

}
