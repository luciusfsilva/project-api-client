package com.dominio.ms_pagamento.infrastructure;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dominio.ms_pagamento.domain.enums.StatusPagamentoEnum;
import com.dominio.ms_pagamento.domain.model.Pagamento;
import com.dominio.ms_pagamento.domain.service.PagamentoService;
import com.dominio.ms_pagamento.infrastructure.controller.PagamentoController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
public class PagamentoControllerTest {
	
	private static final String URL = "/ms-pagamento/api/v1/pagamento";
	private static final String URL_SAVE = URL + "/save";
	private static final String URL_FIND_BY_ID = URL + "/findById/{id}";
	private static final String URL_FIND_ALL = URL + "/findAll";
	
	@InjectMocks
	private PagamentoController pagamentoController;
	
	@Mock
	private PagamentoService pagamentoService;
	
	private MockMvc mockMvc;
	
	Pagamento pagamento = new Pagamento();
	
	List<Pagamento> pagamentos = new ArrayList<>();
	
	ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(pagamentoController).build();
		pagamento = Pagamento.builder()
				.idPedido(UUID.randomUUID())
				.statusPagamento(StatusPagamentoEnum.PENDENTE)
				.valor(new BigDecimal("100.00"))
				.dataPagamento(LocalDateTime.now())
				.build();
		pagamentos.add(pagamento);
		
		mapper.registerModule(new JavaTimeModule());
	}
	
	@Test
	public void testSavePagamento() throws Exception {
		mockMvc.perform(post(URL_SAVE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(pagamento)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testFindById() throws Exception {
		mockMvc.perform(get(URL_FIND_BY_ID, 1L)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFindAll() throws Exception {
		mockMvc.perform(get(URL_FIND_ALL)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
