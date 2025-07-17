package com.dominio.ms_cliente.infrastructure.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.model.Endereco;
import com.dominio.ms_cliente.domain.service.ClienteService;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;
import com.dominio.ms_cliente.infrastructure.dto.ClienteMapper;
import com.dominio.ms_cliente.infrastructure.exception.ClienteException;
import com.dominio.ms_cliente.infrastructure.exception.model.ClienteBadRequestException;
import com.dominio.ms_cliente.infrastructure.exception.model.ClienteInternalServerErrorException;
import com.dominio.ms_cliente.infrastructure.exception.model.ClienteNotFoundException;

@ExtendWith(SpringExtension.class)
@Import(ClienteException.class) // importa o ControllerAdvice
public class ClienteControllerTest {
	
	private static final String URL = "/ms-cliente/api/v1/cliente";
	
	public static final String SAVE = URL + "/save";
	public static final String UPDATE = URL + "/update/{id}";
	public static final String DELETE = URL + "/delete/{id}";
	public static final String FIND_BY_ID = URL + "/{id}";
	public static final String FIND_ALL = URL + "/findAll";
	public static final String FIND_ALL_PAGINATED = URL + "/page";
	
	@InjectMocks
	private ClienteController clienteController;

	@Mock
    private ClienteService clienteService;
	
    @Mock
    private ClienteMapper clienteMapper;
	
	private MockMvc clienteMockMvc;
    
    Cliente cliente = new Cliente();
    
	Endereco endereco = new Endereco();
	
	List<Cliente> clientes = new ArrayList<>();

	ClienteDTO clienteDTO = new ClienteDTO(); 
	
	List<ClienteDTO> clientesDTO = new ArrayList<>();
	
	@BeforeEach
	public void setUp(@Autowired ApplicationContext applicationContext) {
		// Cria mock tipado corretamente
	    clienteMockMvc = MockMvcBuilders
	            .standaloneSetup(clienteController)
	            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
	            .setControllerAdvice(ClienteException.class) // Configura o ControllerAdvice
	            .build();
	    
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
		
		clienteDTO = clienteMapper.toDTO(cliente);
		
		clientesDTO.add(clienteDTO);
	    
	}
	
	// Add test methods here to test the endpoints of ClienteController
	
	@Test
	public void testSaveCliente() throws Exception {
		// Implement test logic for saving a client
		when(clienteService.save(Mockito.any(Cliente.class))).thenReturn(cliente);
		
		clienteMockMvc.perform(post(SAVE)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"John Doe\", \"cpf\":\"12345678901\"}"))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void testUpdateCliente() throws Exception {
		// Implement test logic for updating a client
		when(clienteService.update(Mockito.anyLong(), Mockito.any(Cliente.class))).thenReturn(cliente);
		
		clienteMockMvc.perform(patch(UPDATE, 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Jane Doe\", \"cpf\":\"10987654321\"}"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteCliente() throws Exception {
		// Implement test logic for deleting a client
		
		doNothing().when(clienteService).delete(Mockito.anyLong());	
		
		clienteMockMvc.perform(delete(DELETE, 1L))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void testFindById() throws Exception {
		// Implement test logic for finding a client by ID
		when(clienteService.findById(Mockito.anyLong())).thenReturn(cliente);
		
		clienteMockMvc.perform(get(FIND_BY_ID, 1L))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFindByIdNoContent() throws Exception {
		// Implement test logic for finding a client by ID
		when(clienteService.findById(Mockito.anyLong())).thenReturn(null);
		
		clienteMockMvc.perform(get(FIND_BY_ID, 1L))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testFindAll() throws Exception {
		// Implement test logic for finding all clients
		when(clienteService.findAll()).thenReturn(clientes);
		
		clienteMockMvc.perform(get(FIND_ALL))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFindAllNoContent() throws Exception {
		// Implement test logic for finding all clients
		when(clienteService.findAll()).thenReturn(Collections.emptyList());
		
		clienteMockMvc.perform(get(FIND_ALL))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void testFindAllWithPagination() throws Exception {
		// Implement test logic for finding all clients with pagination
		when(clienteService.findAll(Mockito.any(Pageable.class))).thenReturn(clientesDTO);
		
		clienteMockMvc.perform(get(FIND_ALL_PAGINATED)
		.param("page", "0")
        .param("size", "10")
        .param("sort", "nome,asc"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testFindAllWithPaginationNoContent() throws Exception {
		// Implement test logic for finding all clients with pagination
		when(clienteService.findAll(Mockito.any(Pageable.class))).thenReturn(List.of());
		
		clienteMockMvc.perform(get(FIND_ALL_PAGINATED)
		.param("page", "0")
        .param("size", "10")
        .param("sort", "nome,asc"))
		.andExpect(status().isNoContent());
	}
	
	@Test
    void testClienteNotFoundException() throws Exception {
        Long id = 1L;

        // Simula o service lançando exceção
        when(clienteService.findById(id)).thenThrow(new ClienteNotFoundException("Cliente com ID 1 não encontrado"));

        clienteMockMvc.perform(get("/ms-cliente/api/v1/cliente/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.codigo").value(404))
                .andExpect(jsonPath("$.mensagem").value("Cliente com ID 1 não encontrado"))
                .andExpect(jsonPath("$.dataHora").exists());
    }

    @Test
    void testClienteBadRequestException() throws Exception {
        when(clienteService.findById(anyLong())).thenThrow(new ClienteBadRequestException("Requisição inválida"));

        clienteMockMvc.perform(get("/ms-cliente/api/v1/cliente/99"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value(400))
                .andExpect(jsonPath("$.mensagem").value("Requisição inválida"))
                .andExpect(jsonPath("$.dataHora").exists());
    }

    @Test
    void testClienteInternalServerErrorException() throws Exception {
        when(clienteService.findById(anyLong())).thenThrow(new ClienteInternalServerErrorException("Erro no servidor"));

        clienteMockMvc.perform(get("/ms-cliente/api/v1/cliente/99"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.codigo").value(500))
                .andExpect(jsonPath("$.mensagem").value("Erro no servidor"))
                .andExpect(jsonPath("$.dataHora").exists());
    }

    @Test
    void testGenericException() throws Exception {
        when(clienteService.findById(anyLong())).thenThrow(new RuntimeException("Erro genérico"));

        clienteMockMvc.perform(get("/ms-cliente/api/v1/cliente/99"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value(400))
                .andExpect(jsonPath("$.mensagem").value("Erro genérico"))
                .andExpect(jsonPath("$.dataHora").exists());
    }
}

