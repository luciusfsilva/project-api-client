package com.dominio.ms_cliente.domain.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;
import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.service.ClienteService;
import com.dominio.ms_cliente.domain.service.LogMsClienteService;
import com.dominio.ms_cliente.domain.service.ModelService;
import com.dominio.ms_cliente.domain.service.annotations.TransacaoMsCliente;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;
import com.dominio.ms_cliente.infrastructure.dto.ClienteMapper;
import com.dominio.ms_cliente.infrastructure.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService, ModelService<Cliente> {
	
	private ClienteRepository clienteRepository;
	private ClienteMapper clienteMapper;
	private LogMsClienteService transacaoMsCliente;
	
	public ClienteServiceImpl(ClienteRepository clienteRepository, 
			ClienteMapper clienteMapper, LogMsClienteService transacaoMsCliente) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
		this.transacaoMsCliente = transacaoMsCliente;
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.SAVE)
	@Transactional
	public Cliente save(Cliente cliente) {
		if (Objects.isNull(cliente)) {
			throw new IllegalArgumentException("Cliente cannot be null");
		}
		Cliente existingCliente = clienteRepository.findByCpf(cliente.getCpf());
		if (existingCliente != null) {
			throw new IllegalArgumentException("Cliente with CPF already exists: " + cliente.getCpf());
		}
		Cliente clienteSaved = clienteRepository.save(cliente);
		transacaoMsCliente.saveLog(TransacaoEnum.SAVE);
		return clienteSaved;
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.UPDATE)
	@Transactional
	public Cliente update(Long id, Cliente cliente) {
		if (Objects.isNull(cliente) && Objects.isNull(id)) {
			throw new IllegalArgumentException("Cliente or Cliente ID cannot be null");
		}
		transacaoMsCliente.saveLog(TransacaoEnum.UPDATE);
		Cliente existingCliente = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cliente not found with ID: " + id));
		BeanUtils.copyProperties(cliente, existingCliente, "id");
		Cliente clienteUpdated = clienteRepository.save(existingCliente);
		return clienteUpdated;
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.DELETE)
	public void delete(Long id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		transacaoMsCliente.saveLog(TransacaoEnum.DELETE);
		clienteRepository.deleteById(id);
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.FIND_BY_ID)
	public Cliente findById(Long id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		Optional<Cliente> cliente = clienteRepository.findById(id);
		transacaoMsCliente.saveLog(TransacaoEnum.FIND_BY_ID);
		return cliente.orElseThrow(() -> new IllegalArgumentException("Cliente not found with ID: " + id));
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.FIND_ALL)
	public List<Cliente> findAll() {
		transacaoMsCliente.saveLog(TransacaoEnum.FIND_ALL);
		return clienteRepository.findAll();
	}

	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.PAGEBLE)
	public List<ClienteDTO> findAll(Pageable pageable) {
		transacaoMsCliente.saveLog(TransacaoEnum.PAGEBLE);
		return clienteRepository.findAll(pageable).stream()
				.map(clienteMapper::toDTO)
				.collect(Collectors.toUnmodifiableList());
	}

}
