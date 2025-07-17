package com.dominio.ms_cliente.infrastructure.dto;

import org.mapstruct.Mapper;

import com.dominio.ms_cliente.domain.model.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
	
	EnderecoDTO toDTO(Endereco endereco);

	Endereco toModel(EnderecoDTO enderecoDTO);

}
