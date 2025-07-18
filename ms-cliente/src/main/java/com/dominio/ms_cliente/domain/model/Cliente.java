package com.dominio.ms_cliente.domain.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "CLIENTE")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	
	@Embedded
	private Endereco endereco;
	
	@Column(name = "data_cadastro", nullable = false)
	private LocalDate dataCadastro;
	
	@Column(name = "ativo", nullable = false)
	private Boolean ativo;
	
	@Column(name = "usuario", nullable = false, length = 10)
	private String usuario;
	
	@JsonIgnore
	@Column(name = "senha", nullable = false, length = 8)
	private String senha;
	
	@PrePersist
	public void prePersist() {
		this.dataCadastro = LocalDate.now();
		this.ativo = true;
	}
	
	@PreUpdate
	public void preUpdate() {
		if (this.dataCadastro == null) {
			this.dataCadastro = LocalDate.now();
		}
		if (this.ativo == null) {
			this.ativo = true;
		}
	}

}
