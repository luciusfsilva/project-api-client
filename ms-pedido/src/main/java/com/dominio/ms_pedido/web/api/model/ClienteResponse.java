package com.dominio.ms_pedido.web.api.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponse {
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private EnderecoResponse endereco;
	private LocalDate dataCadastro;
	private Boolean ativo;

}
