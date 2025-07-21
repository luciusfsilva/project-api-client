package com.dominio.ms_pagamento.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.dominio.ms_pagamento.domain.model.DummyModel;
import com.dominio.ms_pagamento.infrastructure.repository.BaseModelRepository;

@ExtendWith(MockitoExtension.class)
public class BaseModelServiceImplTest {
	
	 @Mock
	 private BaseModelRepository<DummyModel, Long> repository;

	 private BaseModelServiceImpl<DummyModel> service;
	 
	 DummyModel model = new DummyModel();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new BaseModelServiceImpl<>(repository);
		
		DummyModel model = new DummyModel();
        model.setNome("Teste");
        model.setId(1L);
	}
	
	@Test
	public void testSave() {
		when(repository.save(model)).thenReturn(model);
		
		DummyModel d = service.save(model);
		assertNotNull(d);
		verify(repository, times(1)).save(model);
	}
	
	@Test
	public void testSaveNull() {
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
			service.save(null);
		});
		assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
		assertEquals("Entity cannot be null", exception.getReason());
		verify(repository, times(0)).save(any());	
		
	}
	
	@Test
	public void testFindById() {
		when(repository.findById(1L)).thenReturn(Optional.of(model));
		
		DummyModel d = service.findById(1L);
		
		assertNotNull(d);
		verify(repository, times(1)).findById(1L);
	}
	
	@Test
	public void testFindByIdNotFound() {
		Long id = 1L;
		when(repository.findById(any())).thenReturn(Optional.empty());
		try {
			service.findById(id);
		} catch (Exception e) {
			assertNotNull(e, "Exception should not be null");
		}
		verify(repository).findById(any());
	}
	
	@Test
	public void testFindByIdNull() {
		try {
			service.findById(null);
		} catch (Exception e) {
			assertNotNull(e, "Exception should not be null");
		}
		verify(repository).findById(any());
	}
	
	@Test
	public void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(new DummyModel()));
		when(service.findAll()).thenReturn(List.of(new DummyModel()));
		java.util.List<DummyModel> entities = service.findAll();
		assertNotNull(entities, "Entities list should not be null");
		verify(repository, times(1)).findAll();
	}

}
