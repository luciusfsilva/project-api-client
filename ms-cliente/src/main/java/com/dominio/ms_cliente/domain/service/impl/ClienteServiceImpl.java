package com.dominio.ms_cliente.domain.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class ClienteServiceImpl implements ClienteService {
	
	private final ModelService<Cliente> modelService;
	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;
	private final LogMsClienteServiceImp transacaoMsClienteImpl;
	
	public ClienteServiceImpl(ModelService<Cliente> modelService, ClienteRepository clienteRepository, 
			ClienteMapper clienteMapper, LogMsClienteServiceImp transacaoMsClienteImpl) {
		this.modelService = modelService;
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
		this.transacaoMsClienteImpl = transacaoMsClienteImpl;
	}
	
	@TransacaoMsCliente(transacao = TransacaoEnum.SAVE)
	public ClienteDTO save(Cliente cliente) {
		if (Objects.isNull(cliente)) {
			throw new IllegalArgumentException("Cliente cannot be null");
		}
		Cliente clienteSaved = modelService.save(cliente);
		transacaoMsClienteImpl.saveLog("save");
		return clienteMapper.toDTO(clienteSaved);
	}
	
	@TransacaoMsCliente(transacao = TransacaoEnum.UPDATE)
	public ClienteDTO update(Cliente cliente) {
		if (Objects.isNull(cliente) || Objects.isNull(cliente.getId())) {
			throw new IllegalArgumentException("Cliente or Cliente ID cannot be null");
		}
		transacaoMsClienteImpl.saveLog("update");
		Cliente clienteUpdated = modelService.save(cliente);
		return clienteMapper.toDTO(clienteUpdated);
	}
	
	@TransacaoMsCliente(transacao = TransacaoEnum.DELETE)
	public void delete(Long id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		transacaoMsClienteImpl.saveLog("delete");
		modelService.delete(id);
	}
	
	@TransacaoMsCliente(transacao = TransacaoEnum.FIND_BY_ID)
	public ClienteDTO findById(Long id) {
		if (Objects.isNull(id)) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		Cliente cliente = modelService.findById(id);
		transacaoMsClienteImpl.saveLog("findById");
		return clienteMapper.toDTO(cliente);
	}
	
	@TransacaoMsCliente(transacao = TransacaoEnum.FIND_ALL)
	public List<ClienteDTO> findAll() {
		transacaoMsClienteImpl.saveLog("findAll");
		return modelService.findAll().stream()
				.map(clienteMapper::toDTO)
				.toList();
	}

	@TransacaoMsCliente(transacao = TransacaoEnum.PAGEBLE)
	@Override
	public List<ClienteDTO> findAll(Pageable pageable) {
		transacaoMsClienteImpl.saveLog("findAll with Pageable");
		return clienteRepository.findAll(pageable).stream()
				.map(clienteMapper::toDTO)
				.collect(Collectors.toUnmodifiableList());
	}

}
