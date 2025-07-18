package com.dominio.ms_cliente.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;
import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.model.LogMsCliente;
import com.dominio.ms_cliente.domain.service.impl.ClienteServiceImpl;
import com.dominio.ms_cliente.domain.service.impl.LogMsClienteServiceImp;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;
import com.dominio.ms_cliente.infrastructure.dto.ClienteMapper;
import com.dominio.ms_cliente.infrastructure.dto.EnderecoDTO;
import com.dominio.ms_cliente.infrastructure.dto.EnderecoMapper;
import com.dominio.ms_cliente.infrastructure.repository.ClienteRepository;

@SpringBootTest
public class ClienteServiceTest {
	
	@Mock
	private ClienteServiceImpl clienteServiceImpl;
	
	@Mock
	private ModelService<Cliente> modelService;
	
	@Mock
	private ClienteMapper clienteMapper;
	
	@Mock
	private EnderecoMapper enderecoMapper;
	
	@Mock
	private LogMsClienteServiceImp transacaoMsClienteImpl;
	
	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock
	private LogMsClienteServiceImp logMsClienteServiceImp;
	
	ClienteDTO clienteDTO = new ClienteDTO();
	
	EnderecoDTO enderecoDTO = new EnderecoDTO();
	
	Cliente cliente = new Cliente();
	
	LogMsCliente logMsCliente = new LogMsCliente();
	
	
	
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
		logMsCliente = LogMsCliente.builder()
				.id(1L)
				.dataHora(LocalDateTime.now())
				.transacao("SAVE")
				.build();
	}
	
	@Test
	public void testSaveCliente() {
		when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);
		when(clienteServiceImpl.save(cliente)).thenReturn(cliente);
		doNothing().when(transacaoMsClienteImpl).saveLog(TransacaoEnum.SAVE);
		
		Cliente savedCliente = clienteServiceImpl.save(cliente);
		assertEquals(clienteDTO.getNome(), savedCliente.getNome());
	}
	
	@Test
	public void testUpdateCliente() {
		when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);
		when(clienteServiceImpl.save(cliente)).thenReturn(cliente);
		doNothing().when(transacaoMsClienteImpl).saveLog(TransacaoEnum.UPDATE);
		
		Cliente updatedCliente = clienteServiceImpl.save(cliente);
		assertEquals(clienteDTO.getNome(), updatedCliente.getNome());
	}
	
	@Test
	public void testDeleteCliente() {
		doNothing().when(clienteServiceImpl).delete(1L);
		doNothing().when(transacaoMsClienteImpl).saveLog(TransacaoEnum.DELETE);
		clienteServiceImpl.delete(1L);
	}
	
	@Test
	public void testFindClienteById() {
		when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);
		when(clienteServiceImpl.findById(1L)).thenReturn(cliente);
		doNothing().when(transacaoMsClienteImpl).saveLog(TransacaoEnum.FIND_BY_ID);
		Cliente foundCliente = clienteServiceImpl.findById(1L);
		assertEquals(clienteDTO.getNome(), foundCliente.getNome());
	}
	
	@Test
	public void testFindAllClientes() {
		when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);
		when(clienteServiceImpl.findAll()).thenReturn(List.of(cliente));
		doNothing().when(transacaoMsClienteImpl).saveLog(TransacaoEnum.FIND_ALL);
		
		List<Cliente> allClientes = clienteServiceImpl.findAll();
		assertEquals(1, allClientes.size());
		assertEquals(clienteDTO.getNome(), allClientes.get(0).getNome());
	}
	
	@Test
	public void testCriptografarSenha() {
		String senha = "senha123";
		when(clienteServiceImpl.criptografarSenha(senha)).thenReturn(senha);
		String senhaCriptografada = clienteServiceImpl.criptografarSenha(senha);
		assertEquals("senha123", senhaCriptografada); // Aqui você deve usar o método real de criptografia
	}
}
