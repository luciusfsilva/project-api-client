package com.dominio.ms_cliente.infrastructure.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import com.dominio.ms_cliente.domain.service.ClienteService;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/ms-cliente/api/v1/cliente")
public class ClienteController {
	
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Operation(summary = "Create a new client")
	@ApiResponses(value = { 
	   @ApiResponse(responseCode = "201", description = "Client created successfully",
	    content = { @Content(mediaType = "application/json",
	      schema = @Schema(implementation = Cliente.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid input provided") })
	@PostMapping("/save")
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
	}
	
	@Operation(summary = "Updated a client by ID")
	@ApiResponses(value = { 
	   @ApiResponse(responseCode = "200", description = "Client updated successfully",
	    content = { @Content(mediaType = "application/json",
	      schema = @Schema(implementation = Cliente.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid input provided") })
	@PatchMapping("/update/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.update(id, cliente));
	}
	
	@Operation(summary = "Delete a client by ID")
	@ApiResponses(value = { 
	   @ApiResponse(responseCode = "200", description = "Client deleted successfully",
	    content = { @Content(mediaType = "application/json",
	      schema = @Schema(implementation = Cliente.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid input provided") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Client find by ID")
	@ApiResponses(value = { 
	   @ApiResponse(responseCode = "201", description = "Client found successfully",
	    content = { @Content(mediaType = "application/json",
	      schema = @Schema(implementation = Cliente.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid input provided") })
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente);
	}
	
	@Operation(summary = "Find all clients")
	@ApiResponses(value = { 
	   @ApiResponse(responseCode = "200", description = "Clients found successfully",
	    content = { @Content(mediaType = "application/json",
	      schema = @Schema(implementation = Cliente.class)) }),
	  @ApiResponse(responseCode = "204", description = "No clients found") })
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<Cliente> clientes = clienteService.findAll();
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clienteService.findAll());
	}
	
	@Operation(summary = "Find all clients with pagination")
	@ApiResponses(value = { 
	   @ApiResponse(responseCode = "200", description = "Clients found successfully with pagination",
	    content = { @Content(mediaType = "application/json",
	      schema = @Schema(implementation = Cliente.class)) }),
	  @ApiResponse(responseCode = "204", description = "No clients found") })
	@GetMapping("/page")
	public ResponseEntity<?> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
		List<ClienteDTO> clientes = clienteService.findAll(pageable);
		if (clientes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clientes);
	}

}
