package com.dominio.ms_pedido.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dominio.ms_pedido.domain.model.LogMsPedido;


public interface LogMsPedidoRepository extends JpaRepository<LogMsPedido, Long> {}
