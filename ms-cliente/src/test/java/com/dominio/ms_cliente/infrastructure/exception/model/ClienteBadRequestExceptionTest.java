package com.dominio.ms_cliente.infrastructure.exception.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ClienteBadRequestExceptionTest {
	
	@Test
    void testExceptionMessage() {
        String mensagem = "CPF inválido";
        ClienteBadRequestException exception = new ClienteBadRequestException(mensagem);

        assertNotNull(exception);
        assertEquals(mensagem, exception.getMessage());
    }

}
