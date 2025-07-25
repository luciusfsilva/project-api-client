package com.dominio.ms_pedido.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dominio.ms_pedido.domain.enums.TransacaoEnum;
import com.dominio.ms_pedido.domain.model.ItemPedido;
import com.dominio.ms_pedido.domain.model.Pedido;
import com.dominio.ms_pedido.domain.service.LogMsPedidoService;
import com.dominio.ms_pedido.domain.service.PedidoService;
import com.dominio.ms_pedido.infrastructure.repository.PedidoRepository;
import com.dominio.ms_pedido.web.api.ClienteServiceApi;
import com.dominio.ms_pedido.web.api.model.ClienteResponse;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	private final PedidoRepository pedidoRepository;
	private final LogMsPedidoService logMsPedidoService;
	private final ClienteServiceApi clienteServiceApi;
	
	public PedidoServiceImpl(PedidoRepository pedidoRepository, LogMsPedidoService logMsPedidoService, 
			ClienteServiceApi clienteServiceApi) {
		this.pedidoRepository = pedidoRepository;
		this.logMsPedidoService = logMsPedidoService;
		this.clienteServiceApi = clienteServiceApi;
	}

	@Override
	public Pedido save(Pedido pedido) {
		if (ObjectUtils.isEmpty(pedido)) {
			throw new IllegalArgumentException("Pedido cannot be null");
		}
		String cpf = findClienteById(pedido.getClienteId()).getCpf();
		pedido.setCpfCliente(cpf);
		if (pedido.getItens().isEmpty()) {
			throw new IllegalArgumentException("Pedido must have at least one item");
		} else {
			for (ItemPedido item : pedido.getItens()) {
				item.setPedido(pedido);
			}
		}
		logMsPedidoService.saveLog(TransacaoEnum.SAVE);
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido update(UUID id, Pedido model) {
		if (ObjectUtils.isEmpty(model) && id == null) {
			throw new IllegalArgumentException("Pedido and ID cannot be null");
		}
		Pedido existingPedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pedido not found with id: " + id));
		logMsPedidoService.saveLog(TransacaoEnum.UPDATE);
		BeanUtils.copyProperties(model, existingPedido, "id");
		pedidoRepository.save(existingPedido);
		return model;
	}

	@Override
	public void delete(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		Pedido existingPedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pedido not found with id: " + id));
		logMsPedidoService.saveLog(TransacaoEnum.DELETE);
		pedidoRepository.delete(existingPedido);
	}

	@Override
	public Pedido findById(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		logMsPedidoService.saveLog(TransacaoEnum.FIND_BY_ID);
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pedido not found with id: " + id));
	}

	@Override
	public List<Pedido> findAll() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		logMsPedidoService.saveLog(TransacaoEnum.FIND_ALL);
		return pedidos != null ? pedidos : new ArrayList<>();
	}
	
	public ClienteResponse findClienteById(Long id) {
		ClienteResponse clienteResponse = clienteServiceApi.consultarClientePorId(id);
		if (ObjectUtils.isEmpty(clienteResponse)) {
			throw new IllegalArgumentException("Cliente not found");
		}
		logMsPedidoService.saveLog(TransacaoEnum.FIND_CLIENTE_BY_ID);
		return clienteResponse;
	}

}
