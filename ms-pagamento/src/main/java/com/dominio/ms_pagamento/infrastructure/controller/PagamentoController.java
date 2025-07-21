package com.dominio.ms_pagamento.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominio.ms_pagamento.domain.model.Pagamento;
import com.dominio.ms_pagamento.domain.service.PagamentoService;

@RestController
@RequestMapping("/ms-pagamento/api/v1/pagamento")
public class PagamentoController {
	
	private final PagamentoService pagamentoService;
	
	public PagamentoController(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Pagamento pagamento) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(pagamentoService.save(pagamento));
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(pagamentoService.findById(id));
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(pagamentoService.findAll());
	}
	
}
