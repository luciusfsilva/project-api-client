package com.dominio.ms_cliente.infrastructure.container;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
public class ClienteRepositoryTest {
	
	@SuppressWarnings("resource")
	@Container
    static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @Test
    void testContainerRunning() {
        assert(postgresContainer.isRunning());
        System.out.println("PostgreSQL iniciado: " + postgresContainer.getJdbcUrl());
    }

}
