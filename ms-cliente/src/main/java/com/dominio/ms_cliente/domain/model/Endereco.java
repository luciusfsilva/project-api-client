package com.dominio.ms_cliente.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

	@Column(nullable = false, length = 100)
	private String logradouro;
	
	@Column(nullable = false, length = 10)
	private String numero;
	
	@Column(length = 50)
	private String complemento;
	
	@Column(nullable = false, length = 50)
	private String bairro;
	
	@Column(nullable = false, length = 50)
	private String cidade;
	
	@Column(nullable = false, length = 2)
	private String estado;
	
	@Column(nullable = false, length = 8)
	private String cep;

}
