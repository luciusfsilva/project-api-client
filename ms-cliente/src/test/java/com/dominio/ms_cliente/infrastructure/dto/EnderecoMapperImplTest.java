package com.dominio.ms_cliente.infrastructure.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.dominio.ms_cliente.domain.model.Endereco;

public class EnderecoMapperImplTest {
	
	private final EnderecoMapper enderecoMapper = new EnderecoMapperImpl();

    @Test
    void testToDTO() {
        Endereco endereco = Endereco.builder()
                .logradouro("Rua A")
                .numero("123")
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .cep("01234-567")
                .complemento("Apto 45")
                .build();

        var dto = enderecoMapper.toDTO(endereco);

        assertNotNull(dto);
        assertEquals("Rua A", dto.getLogradouro());
        assertEquals("123", dto.getNumero());
        assertEquals("Centro", dto.getBairro());
        assertEquals("São Paulo", dto.getCidade());
        assertEquals("SP", dto.getEstado());
        assertEquals("01234-567", dto.getCep());
        assertEquals("Apto 45", dto.getComplemento());
    }

    @Test
    void testToModel() {
        EnderecoDTO dto = EnderecoDTO.builder()
                .logradouro("Rua B")
                .numero("456")
                .bairro("Bela Vista")
                .cidade("Rio de Janeiro")
                .estado("RJ")
                .cep("76543-210")
                .complemento("Casa")
                .build();

        var endereco = enderecoMapper.toModel(dto);

        assertNotNull(endereco);
        assertEquals("Rua B", endereco.getLogradouro());
        assertEquals("456", endereco.getNumero());
        assertEquals("Bela Vista", endereco.getBairro());
        assertEquals("Rio de Janeiro", endereco.getCidade());
        assertEquals("RJ", endereco.getEstado());
        assertEquals("76543-210", endereco.getCep());
        assertEquals("Casa", endereco.getComplemento());
    }

    @Test
    void testToDTO_Null() {
        assertNull(enderecoMapper.toDTO(null));
    }

    @Test
    void testToModel_Null() {
        assertNull(enderecoMapper.toModel(null));
    }

}
