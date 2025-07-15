package com.dominio.ms_cliente.infrastructure.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ModelRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {}
