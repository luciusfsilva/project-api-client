package com.dominio.ms_cliente.infrastructure.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.model.Endereco;

public class ClienteMapperImplTest {
	
	private final ClienteMapper clienteMapper = new ClienteMapperImpl();

    @Test
    void testToDTO() {
        Endereco endereco = Endereco.builder()
                .logradouro("Rua das Flores")
                .numero("123")
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .cep("01234-567")
                .complemento("Apto 12")
                .build();

        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Carlos Silva")
                .cpf("12345678901")
                .email("carlos@test.com")
                .endereco(endereco)
                .dataCadastro(LocalDate.now())
                .ativo(true)
                .build();

        ClienteDTO dto = clienteMapper.toDTO(cliente);

        assertNotNull(dto);
        assertEquals("Carlos Silva", dto.getNome());
        assertEquals("carlos@test.com", dto.getEmail());
        assertNotNull(dto.getEndereco());
        assertEquals("Centro", dto.getEndereco().getBairro());
        assertEquals("01234-567", dto.getEndereco().getCep());
    }

    @Test
    void testToModel() {
        EnderecoDTO enderecoDTO = EnderecoDTO.builder()
                .logradouro("Av. Brasil")
                .numero("456")
                .bairro("Copacabana")
                .cidade("Rio de Janeiro")
                .estado("RJ")
                .cep("76543-210")
                .complemento("Casa")
                .build();

        ClienteDTO clienteDTO = ClienteDTO.builder()
                .nome("Ana Souza")
                .email("ana@test.com")
                .endereco(enderecoDTO)
                .build();

        Cliente cliente = clienteMapper.toModel(clienteDTO);

        assertNotNull(cliente);
        assertEquals("Ana Souza", cliente.getNome());
        assertEquals("ana@test.com", cliente.getEmail());
        assertNotNull(cliente.getEndereco());
        assertEquals("Copacabana", cliente.getEndereco().getBairro());
        assertEquals("76543-210", cliente.getEndereco().getCep());
    }

    @Test
    void testToDTO_Null() {
        assertNull(clienteMapper.toDTO(null));
    }

    @Test
    void testToModel_Null() {
        assertNull(clienteMapper.toModel(null));
    }

}
