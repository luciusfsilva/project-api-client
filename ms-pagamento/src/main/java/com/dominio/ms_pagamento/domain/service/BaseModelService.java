package com.dominio.ms_pagamento.domain.service;

import java.util.List;

public interface BaseModelService <T>{

	public T save(T t);
	
	public T findById(Long id);
	
	public List<T> findAll();
	
}
