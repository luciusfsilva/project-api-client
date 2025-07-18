package com.dominio.ms_cliente.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms-cliente/api/v1/cliente")
public class LoginController {
	
	@PostMapping("/login")
	public ResponseEntity<?> login(String usuario, String senha) {
		return ResponseEntity.ok("Login realizado com sucesso");
	}

}
