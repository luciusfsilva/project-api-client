package com.dominio.ms_pagamento.infrastructure.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseModelRepository <T, ID extends Serializable> extends JpaRepository<T, ID>{}
