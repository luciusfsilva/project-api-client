package com.dominio.ms_cliente.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.dominio.ms_cliente.infrastructure.exception.model.ClienteValidationException;

public class ClienteValidationExceptionTest {

	@Test
    void testExceptionMessage() {
        String mensagem = "CPF inválido";
        ClienteValidationException exception = new ClienteValidationException(mensagem);

        assertNotNull(exception);
        assertEquals(mensagem, exception.getMessage());
    }
}
