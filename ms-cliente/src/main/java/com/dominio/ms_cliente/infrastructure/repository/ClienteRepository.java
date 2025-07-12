package com.dominio.ms_cliente.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominio.ms_cliente.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
