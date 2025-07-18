package com.dominio.ms_pedido.web.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dominio.ms_pedido.web.api.model.ClienteResponse;

@FeignClient(name = "ms-cliente", url = "${ms.cliente.url}")
public interface ClienteServiceApi {
	
	static final String CLIENTE_PATH = "/ms-cliente/api/v1/cliente/{id}";

	@GetMapping(CLIENTE_PATH)
	ClienteResponse consultarClientePorId(@PathVariable Long id);
}
