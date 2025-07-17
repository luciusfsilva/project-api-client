package com.dominio.ms_cliente.domain.service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.dominio.ms_cliente.domain.model.Cliente;

public class ClienteTest {

	@Test
    public void testPrePersist() {
        // Arrange
        Cliente cliente = Cliente.builder()
                .nome("Teste")
                .cpf("12345678901")
                .email("teste@email.com")
                .build();

        // Act
        cliente.prePersist();

        // Assert
        assertNotNull(cliente.getDataCadastro());
        assertEquals(LocalDate.now(), cliente.getDataCadastro());
        assertTrue(cliente.getAtivo());
    }
	
	@Test
    public void testPreUpdateWithNulls() {
        // Arrange
        Cliente cliente = new Cliente();

        // Act
        cliente.preUpdate();

        // Assert
        assertNotNull(cliente.getDataCadastro());
        assertEquals(LocalDate.now(), cliente.getDataCadastro());
        assertTrue(cliente.getAtivo());
    }

    @Test
    public void testPreUpdateWithoutChanges() {
        // Arrange
        Cliente cliente = Cliente.builder()
                .dataCadastro(LocalDate.of(2022, 1, 1))
                .ativo(false)
                .build();

        // Act
        cliente.preUpdate();

        // Assert
        assertEquals(LocalDate.of(2022, 1, 1), cliente.getDataCadastro());
        assertFalse(cliente.getAtivo());
    }
}
