package com.dominio.ms_pagamento.domain.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.dominio.ms_pagamento.domain.service.BaseModelService;
import com.dominio.ms_pagamento.infrastructure.repository.BaseModelRepository;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

@Log
public class BaseModelServiceImpl<T> implements BaseModelService<T> {
	
	private final BaseModelRepository<T, Long> baseModelRepository;
	
	public BaseModelServiceImpl(BaseModelRepository<T, Long> baseModelRepository) {
		this.baseModelRepository = baseModelRepository;
	}

	@Override
	@Transactional
	public T save(T t) {
		if (t == null) {
			log.severe("Attempted to save a null entity");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity cannot be null");
		}

		return baseModelRepository.save(t);
	}

	@Override
	public T findById(Long id) {
		return baseModelRepository.findById(id)
				.map(entity -> {
					log.info("Entity found with id: " + id);
					return entity;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found with id: " + id));
	}

	@Override
	public List<T> findAll() {
		List<T> entities = baseModelRepository.findAll();
		if (entities.isEmpty()) {
			log.warning("No entities found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No entities found");
		}
		return entities;
	}

}
