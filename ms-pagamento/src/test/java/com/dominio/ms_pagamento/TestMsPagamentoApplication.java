package com.dominio.ms_pagamento;

import org.springframework.boot.SpringApplication;

public class TestMsPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.from(MsPagamentoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
