package com.dominio.ms_cliente.infrastructure.repository;

import java.util.Optional;

import com.dominio.ms_cliente.domain.model.Cliente;

public interface ClienteRepository extends ModelRepository<Cliente, Long> {
	
	Optional<Cliente> findByCpf(String cpf);
}
