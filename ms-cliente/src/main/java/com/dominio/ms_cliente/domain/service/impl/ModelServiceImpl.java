package com.dominio.ms_cliente.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dominio.ms_cliente.domain.service.ModelService;
import com.dominio.ms_cliente.infrastructure.repository.ModelRepository;

@Service
public class ModelServiceImpl<T> implements ModelService<T> {
	
	private ModelRepository<T> repository;
	
	public ModelServiceImpl(ModelRepository<T> repository) {
		this.repository = repository;
	}

	@Override
	public T save(T model) {
		return repository.save(model);
	}

	@Override
	public T update(T model) {
		return repository.save(model);
	}

	@Override
	public void delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Entity with id " + id + " does not exist.");
		}		
	}

	@Override
	public T findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Entity with id " + id + " does not exist."));
	}

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}


}
