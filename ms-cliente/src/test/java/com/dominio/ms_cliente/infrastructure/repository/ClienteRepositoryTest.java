package com.dominio.ms_cliente.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.model.Endereco;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;
import com.dominio.ms_cliente.infrastructure.dto.ClienteMapper;

@DataJpaTest
public class ClienteRepositoryTest {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Mock
	ClienteMapper clienteMapper;
	
	Cliente cliente = new Cliente();
	
	Endereco endereco = new Endereco();
	
	List<Cliente> clientes = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		endereco = Endereco.builder()
				.logradouro("Rua das Flores")
				.numero("123")
				.bairro("Jardim das Rosas")
				.cidade("São Paulo")
				.estado("SP")
				.cep("01234-567")
				.build();
		
		cliente = Cliente.builder()
				.id(1L)
				.nome("Carlos Silva")
				.cpf("12345678901")
				.email("carlos@test.com")
				.endereco(endereco)
				.dataCadastro(LocalDate.now())
				.ativo(true)
				.build();
		
		clientes.add(cliente);
	}
	
	@Test
	public void pageableCliente() {
		Pageable pageable = Pageable.ofSize(10).withPage(0);
		List<ClienteDTO> clienteList = clienteRepository.findAll(pageable)
				.stream()
				.map(clienteMapper::toDTO)
				.collect(Collectors.toUnmodifiableList());
		
		assertTrue(clienteList.size() <= 0);
	}

}
