package com.dominio.ms_pedido;

import org.springframework.boot.SpringApplication;

public class TestMsPedidoApplication {

	public static void main(String[] args) {
		SpringApplication.from(MsPedidoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
