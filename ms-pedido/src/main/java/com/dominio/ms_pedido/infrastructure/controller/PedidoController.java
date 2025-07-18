package com.dominio.ms_pedido.infrastructure.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominio.ms_pedido.domain.model.Pedido;
import com.dominio.ms_pedido.domain.service.PedidoService;

@RestController
@RequestMapping("/ms-pedido/api/v1/pedido")
public class PedidoController {
	
	private final PedidoService pedidoService;
	
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Pedido pedido) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(pedidoService.save(pedido));
	}
	
	@PutMapping("/update/{Id}")
	public ResponseEntity<?> update(@PathVariable UUID Id,  @RequestBody Pedido pedido) {
		return ResponseEntity.ok(pedidoService.update(pedido.getId(), pedido));
	}
	
	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<?> delete(@PathVariable UUID Id) {
		pedidoService.delete(Id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/find/{Id}")
	public ResponseEntity<?> findById(@PathVariable UUID Id) {
		return ResponseEntity.ok(pedidoService.findById(Id));
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		if (pedidoService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pedidoService.findAll());
	}

}
