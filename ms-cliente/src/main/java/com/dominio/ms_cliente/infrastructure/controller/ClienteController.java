package com.dominio.ms_cliente.infrastructure.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.service.impl.ClienteServiceImpl;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;

@RestController
@RequestMapping("/ms-cliente")
public class ClienteController {
	
	private final ClienteServiceImpl clienteService;
	
	public ClienteController(ClienteServiceImpl clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.save(cliente));
	}
	
	@PatchMapping("/update")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.update(cliente));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		ClienteDTO clienteDTO = clienteService.findById(id);
		if (clienteDTO == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(clienteDTO);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<ClienteDTO> clientes = clienteService.findAll();
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clienteService.findAll());
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> findAll(Pageable pageable) {
		return ResponseEntity.ok(clienteService.findAll(pageable));
	}

}
