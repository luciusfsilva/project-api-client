package com.dominio.ms_cliente.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;
import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.model.Endereco;
import com.dominio.ms_cliente.domain.service.LogMsClienteService;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;
import com.dominio.ms_cliente.infrastructure.dto.ClienteMapper;
import com.dominio.ms_cliente.infrastructure.repository.ClienteRepository;

public class ClienteServiceImplTest {

	@InjectMocks
	private ClienteServiceImpl clienteServiceImpl;

	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private ClienteMapper clienteMapper;
	
	@Mock
	private LogMsClienteService transacaoMsCliente;

	Cliente cliente = new Cliente();
	
	ClienteDTO clienteDTO = new ClienteDTO();

	Endereco endereco = new Endereco();

	List<Cliente> clientes = new ArrayList<>();
	
	List<ClienteDTO> clientesDTO = new ArrayList<>();
	
	Pageable pageable;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		endereco = Endereco.builder()
				.logradouro("Rua das Flores")
				.numero("123")
				.bairro("Jardim das Rosas")
				.cidade("São Paulo")
				.estado("SP")
				.cep("01234-567")
				.build();
		
		cliente = Cliente.builder()
				.id(2L)
				.nome("Carlos Silva")
				.cpf("12345678901")
				.email("carlos@test.com")
				.endereco(endereco)
				.dataCadastro(LocalDate.now())
				.ativo(true)
				.build();
		
		clientes.add(cliente);
		
		pageable = PageRequest.of(0, 10);
	}

	@Test
	public void testSaveCliente() {
		when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(null);
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		doNothing().when(transacaoMsCliente).saveLog(TransacaoEnum.SAVE);
		Cliente resultado = clienteServiceImpl.save(cliente);
		assertNotNull(resultado);
		assertEquals(cliente.getId(), resultado.getId());
		verify(clienteRepository, times(1)).save(cliente);
		verify(transacaoMsCliente, times(1)).saveLog(TransacaoEnum.SAVE);
	}
	
	@Test
	public void testSaveClienteNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			clienteServiceImpl.save(null);
		});
		
		assertEquals("Cliente cannot be null", exception.getMessage());
	}
	
	@Test
	public void testSaveExistente() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(cliente);
			clienteServiceImpl.save(cliente);
		});
		
		assertEquals("Cliente with CPF already exists: " + cliente.getCpf(), exception.getMessage());
	}

	@Test
	public void testUpdateCliente() {
		when(clienteRepository.findById(2L)).thenReturn(java.util.Optional.of(cliente));
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		doNothing().when(transacaoMsCliente).saveLog(TransacaoEnum.UPDATE);
		Cliente resultado = clienteServiceImpl.update(2L, cliente);
		assertNotNull(resultado);
		verify(clienteRepository, times(1)).save(cliente);
		verify(transacaoMsCliente, times(1)).saveLog(TransacaoEnum.UPDATE);
	}
	
	@Test
	public void testUpdateClienteNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			clienteServiceImpl.update(null, null);
		});
		
		assertEquals("Cliente or Cliente ID cannot be null", exception.getMessage());
	}
	
	@Test
	public void testUpdateClienteIdNaoEncontrado() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			when(clienteRepository.findById(3L)).thenReturn(Optional.empty());
			clienteServiceImpl.update(3L, cliente);
		});
		
		assertEquals("Cliente not found with ID: 3", exception.getMessage());
	}

	@Test
	public void testFindById() {
		when(clienteRepository.findById(2L)).thenReturn(java.util.Optional.of(cliente));
		doNothing().when(transacaoMsCliente).saveLog(TransacaoEnum.FIND_BY_ID);
		Cliente resultado = clienteServiceImpl.findById(2L);
		assertNotNull(resultado);
		assertEquals(cliente.getId(), resultado.getId());
		verify(clienteRepository, times(1)).findById(2L);
	    verify(transacaoMsCliente, times(1)).saveLog(TransacaoEnum.FIND_BY_ID);
	}
	
	@Test
	public void testFindByIdNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			clienteServiceImpl.findById(null);
		});
		
		assertEquals("ID cannot be null", exception.getMessage());
	}
	
	@Test
	public void testFindByIdNaoEcontrado() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			when(clienteRepository.findById(3L)).thenReturn(Optional.empty());
			clienteServiceImpl.findById(3L);
		});
		
		assertEquals("Cliente not found with ID: 3", exception.getMessage());
	}

	@Test
	public void testFindAll() {
		when(clienteServiceImpl.findAll()).thenReturn(clientes);
		when(clienteRepository.findAll()).thenReturn(clientes);
		List<Cliente> resultado = clienteServiceImpl.findAll();
		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		
		verify(clienteRepository, times(1)).findAll();
	}

	@Test
	public void testDelete() {
		when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
		doNothing().when(transacaoMsCliente).saveLog(TransacaoEnum.DELETE);
		clienteServiceImpl.delete(1L);
		
		verify(clienteRepository, times(1)).deleteById(1L);
	    verify(transacaoMsCliente, times(1)).saveLog(TransacaoEnum.DELETE);
	}
	
	@Test
	public void testDeleteIdNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			clienteServiceImpl.delete(null);
		});
		
		assertEquals("ID cannot be null", exception.getMessage());
	}

	@Test
	public void testClientePageable() {
		Page<Cliente> page = new PageImpl<>(List.of(cliente));
	    when(clienteRepository.findAll(pageable)).thenReturn(page);

	    // Simula o comportamento do mapper
	    when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(new ClienteDTO());

	    // Evita execução real do log
	    doNothing().when(transacaoMsCliente).saveLog(TransacaoEnum.PAGEBLE);

	    // Executa
	    List<ClienteDTO> resultado = clienteServiceImpl.findAll(pageable);

	    // Verificações
	    assertNotNull(resultado);
	    assertEquals(1, resultado.size());
	    verify(clienteRepository, times(1)).findAll(pageable);
	    verify(clienteMapper, times(1)).toDTO(any(Cliente.class));
	    verify(transacaoMsCliente, times(1)).saveLog(TransacaoEnum.PAGEBLE);
	}
}