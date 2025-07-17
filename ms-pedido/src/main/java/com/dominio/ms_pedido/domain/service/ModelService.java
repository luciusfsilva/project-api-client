package com.dominio.ms_pedido.domain.service;

import java.util.List;
import java.util.UUID;

public interface ModelService<T> {
	
	public T save(T model);
	public T update(UUID id, T model);
	public void delete(UUID id);
	public T findById(UUID id);
	public List<T> findAll();

}
