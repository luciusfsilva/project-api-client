package com.dominio.ms_cliente.domain.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;

public interface ClienteService {

	public List<ClienteDTO> findAll(Pageable pageable);
}
