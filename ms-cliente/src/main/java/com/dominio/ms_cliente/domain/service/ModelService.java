package com.dominio.ms_cliente.domain.service;

import java.util.List;

public interface ModelService <T> {
	
	public T save(T model);
	public T update(T model);
	public void delete(Long id);
	public T findById(Long id);
	public List<T> findAll();

}
