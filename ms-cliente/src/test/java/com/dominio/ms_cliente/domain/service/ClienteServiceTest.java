package com.dominio.ms_cliente.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.service.impl.LogMsClienteServiceImp;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;
import com.dominio.ms_cliente.infrastructure.dto.ClienteMapper;
import com.dominio.ms_cliente.infrastructure.dto.EnderecoDTO;
import com.dominio.ms_cliente.infrastructure.dto.EnderecoMapper;

@SpringBootTest
public class ClienteServiceTest {
	
	@InjectMocks
	private ModelService<Cliente> modelService;
	
	@Mock
	private ClienteMapper clienteMapper;
	
	@Mock
	private EnderecoMapper enderecoMapper;
	
	@Mock
	private LogMsClienteServiceImp transacaoMsClienteImpl;
	
	ClienteDTO clienteDTO = new ClienteDTO();
	
	EnderecoDTO enderecoDTO = new EnderecoDTO();
	
	Cliente cliente = new Cliente();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		enderecoDTO = EnderecoDTO.builder()
				.logradouro("Rua das Flores")
				.numero("123")
				.bairro("Jardim das Rosas")
				.cidade("São Paulo")
				.estado("SP")
				.cep("01234-567")
				.build();
		
		clienteDTO = ClienteDTO.builder()
				.nome("Carlos Silva")
				.email("carlos@teste.com")
				.endereco(enderecoDTO)
				.build();
		cliente = Cliente.builder()
				.id(1L)
				.nome("Carlos Silva")
				.email("carlos@teste.com")
				.endereco(enderecoMapper.toModel(enderecoDTO))
				.dataCadastro(LocalDate.now())
				.ativo(true)
				.build();
	}
	
	@Test
	public void testSaveCliente() {
		when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(clienteDTO);
		when(modelService.save(any(Cliente.class))).thenReturn(cliente);
		verify(clienteMapper).toDTO(any(Cliente.class));
		verify(modelService).save(any(Cliente.class));
		
		Cliente savedCliente = modelService.save(cliente);
		assertEquals(clienteDTO.getNome(), savedCliente.getNome());
	}
	
	@Test
	public void testUpdateCliente() {
		when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(clienteDTO);
		when(modelService.save(any(Cliente.class))).thenReturn(cliente);
		verify(clienteMapper).toDTO(any(Cliente.class));
		verify(modelService).save(any(Cliente.class));
		
		Cliente updatedCliente = modelService.save(cliente);
		assertEquals(clienteDTO.getNome(), updatedCliente.getNome());
	}
	
	@Test
	public void testDeleteCliente() {
		verify(modelService).delete(any(Long.class));
		
		modelService.delete(1L);
		verify(modelService).delete(1L);
	}
	
	@Test
	public void testFindClienteById() {
		when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(clienteDTO);
		when(modelService.findById(any(Long.class))).thenReturn(cliente);
		verify(clienteMapper).toDTO(any(Cliente.class));
		verify(modelService).findById(any(Long.class));
		
		Cliente foundCliente = modelService.findById(1L);
		assertEquals(clienteDTO.getNome(), foundCliente.getNome());
	}
	
	@Test
	public void testFindAllClientes() {
		when(clienteMapper.toDTO(any(Cliente.class))).thenReturn(clienteDTO);
		when(modelService.findAll()).thenReturn(List.of(cliente));
		verify(clienteMapper).toDTO(any(Cliente.class));
		verify(modelService).findAll();
		
		List<Cliente> allClientes = modelService.findAll();
		assertEquals(1, allClientes.size());
		assertEquals(clienteDTO.getNome(), allClientes.get(0).getNome());
	}

}
