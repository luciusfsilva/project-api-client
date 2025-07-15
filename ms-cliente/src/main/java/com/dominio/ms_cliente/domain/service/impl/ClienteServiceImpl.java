package com.dominio.ms_cliente.domain.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dominio.ms_cliente.domain.enums.TransacaoEnum;
import com.dominio.ms_cliente.domain.model.Cliente;
import com.dominio.ms_cliente.domain.service.ClienteService;
import com.dominio.ms_cliente.domain.service.ModelService;
import com.dominio.ms_cliente.domain.service.annotations.TransacaoMsCliente;
import com.dominio.ms_cliente.infrastructure.dto.ClienteDTO;
import com.dominio.ms_cliente.infrastructure.dto.ClienteMapper;
import com.dominio.ms_cliente.infrastructure.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService, ModelService<Cliente> {
	
	private ClienteRepository clienteRepository;
	private ClienteMapper clienteMapper;
	private LogMsClienteServiceImp transacaoMsClienteImpl;
	
	public ClienteServiceImpl(ClienteRepository clienteRepository, 
			ClienteMapper clienteMapper, LogMsClienteServiceImp transacaoMsClienteImpl) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
		this.transacaoMsClienteImpl = transacaoMsClienteImpl;
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.SAVE)
	public Cliente save(Cliente cliente) {
		if (Objects.isNull(cliente)) {
			throw new IllegalArgumentException("Cliente cannot be null");
		}
		Cliente clienteSaved = clienteRepository.save(cliente);
		transacaoMsClienteImpl.saveLog("save");
		return clienteSaved;
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.UPDATE)
	public Cliente update(Long id, Cliente cliente) {
		if (Objects.isNull(cliente) && Objects.isNull(id)) {
			throw new IllegalArgumentException("Cliente or Cliente ID cannot be null");
		}
		transacaoMsClienteImpl.saveLog("update");
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
		transacaoMsClienteImpl.saveLog("delete");
		clienteRepository.deleteById(id);
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.FIND_BY_ID)
	public Cliente findById(Long id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		Optional<Cliente> cliente = clienteRepository.findById(id);
		transacaoMsClienteImpl.saveLog("findById");
		return cliente.orElseThrow(() -> new IllegalArgumentException("Cliente not found with ID: " + id));
	}
	
	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.FIND_ALL)
	public List<Cliente> findAll() {
		transacaoMsClienteImpl.saveLog("findAll");
		return clienteRepository.findAll();
	}

	@Override
	@TransacaoMsCliente(transacao = TransacaoEnum.PAGEBLE)
	public List<ClienteDTO> findAll(Pageable pageable) {
		transacaoMsClienteImpl.saveLog("findAll with Pageable");
		return clienteRepository.findAll(pageable).stream()
				.map(clienteMapper::toDTO)
				.collect(Collectors.toUnmodifiableList());
	}

}
