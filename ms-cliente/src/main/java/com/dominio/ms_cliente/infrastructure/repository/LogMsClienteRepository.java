package com.dominio.ms_cliente.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominio.ms_cliente.domain.model.LogMsCliente;

public interface LogMsClienteRepository extends JpaRepository<LogMsCliente, Long> {}
