package com.dominio.ms_cliente.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository<T> extends JpaRepository<T, Long> {}
