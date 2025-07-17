package com.dominio.ms_pedido.infraestructure.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dominio.ms_pedido.domain.enums.StatusPedidoEnum;
import com.dominio.ms_pedido.domain.model.Pedido;
import com.dominio.ms_pedido.domain.service.PedidoService;
import com.dominio.ms_pedido.infrastructure.controller.PedidoController;
import com.dominio.ms_pedido.infrastructure.exception.PedidoException;
import com.dominio.ms_pedido.infrastructure.exception.model.PedidoBadRequestException;
import com.dominio.ms_pedido.infrastructure.exception.model.PedidoInternalServerException;
import com.dominio.ms_pedido.infrastructure.exception.model.PedidoNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@Import(PedidoException.class)
public class PedidoControllerTest {

	private static final String URL = "/ms-pedido/api/v1/pedido";
	
	public static final String SAVE = URL + "/save";
	public static final String UPDATE = URL + "/update/{id}";
	public static final String DELETE = URL + "/delete/{id}";
	public static final String FIND_BY_ID = URL + "/find/{id}";
	public static final String FIND_ALL = URL + "/findAll";

	@InjectMocks
	private PedidoController pedidoController;
	
	@Mock
	private PedidoService pedidoService;
	
	MockMvc pedidoMockMvc;
	
	Pedido pedido = new Pedido();
	
	List<Pedido> pedidos = new ArrayList<>();
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	@BeforeEach
	public void setUp() {
		pedidoMockMvc = MockMvcBuilders.standaloneSetup(pedidoController)
				.setControllerAdvice(PedidoException.class)
				.build();
		
		pedido = Pedido.builder()
				.id(UUID.randomUUID())
				.clienteId(UUID.randomUUID())
				.total(new BigDecimal(100.0))
				.status(StatusPedidoEnum.PENDENTE)
				.dataHora(LocalDateTime.now())
				.itens(List.of())
				.build();
		
		pedidos.add(pedido);
		objectMapper.registerModule(new JavaTimeModule());
	}
	
	@Test
	public void saveTest() throws Exception {
		pedidoMockMvc.perform(post(SAVE)
				.contentType("application/json")
				.content("{\"clienteId\":\"" + pedido.getClienteId() + "\", \"total\":100.0, \"status\":\"PENDENTE\"}"))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void updateTest() throws Exception {
		pedidoMockMvc.perform(put(UPDATE, pedido.getId())
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(pedido)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteTest() throws Exception {
		doNothing().when(pedidoService).delete(UUID.randomUUID());
		pedidoMockMvc.perform(delete(DELETE, pedido.getId()))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void findByIdTest() throws Exception {
		when(pedidoService.findById(pedido.getId())).thenReturn(pedido);
		pedidoMockMvc.perform(get(FIND_BY_ID, pedido.getId()))
				.andExpect(status().isOk());
	}
	
	@Test
	public void findAllTest() throws Exception {
		when(pedidoService.findAll()).thenReturn(pedidos);
		pedidoMockMvc.perform(get(FIND_ALL))
				.andExpect(status().isOk());
	}
	
	@Test
	public void findAllNoContentTest() throws Exception {
		when(pedidoService.findAll()).thenReturn(new ArrayList<>());
		pedidoMockMvc.perform(get(FIND_ALL))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void pedidoBadRequestTest() throws Exception {
		when(pedidoService.findById(UUID.randomUUID())).thenThrow(new PedidoBadRequestException("Pedido Bad Request"));
		pedidoMockMvc.perform(get(FIND_BY_ID, 0))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void pedidoNotFoundTest() throws Exception {
		UUID id = UUID.randomUUID();
		when(pedidoService.findById(id)).thenThrow(new PedidoNotFoundException("Pedido not found"));
		pedidoMockMvc.perform(get(FIND_BY_ID, id))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void pedidoInternalServerErrorTest() throws Exception {
		UUID id = UUID.randomUUID();
		when(pedidoService.findById(id)).thenThrow(new PedidoInternalServerException("Internal Server Error"));
		pedidoMockMvc.perform(get(FIND_BY_ID, id))
				.andExpect(status().isInternalServerError());
	}
	
	@Test
	public void pedidoValidationExceptionTest() throws Exception {
		pedidoMockMvc.perform(post(SAVE)
				.contentType("application/json")
				.content("{\"clienteId\":\"" + null + "\", \"total\":null, \"status\":\"PENDENTE\"}"))
				.andExpect(status().isBadRequest());
	}
}
