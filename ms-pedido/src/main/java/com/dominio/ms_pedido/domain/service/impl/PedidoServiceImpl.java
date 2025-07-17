package com.dominio.ms_pedido.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dominio.ms_pedido.domain.model.Pedido;
import com.dominio.ms_pedido.domain.service.PedidoService;
import com.dominio.ms_pedido.infrastructure.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	private final PedidoRepository pedidoRepository;
	
	public PedidoServiceImpl(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	@Override
	public Pedido save(Pedido pedido) {
		if (ObjectUtils.isEmpty(pedido)) {
			throw new IllegalArgumentException("Pedido cannot be null");
		}
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido update(UUID id, Pedido model) {
		if (ObjectUtils.isEmpty(model) && id == null) {
			throw new IllegalArgumentException("Pedido and ID cannot be null");
		}
		Pedido existingPedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pedido not found with id: " + id));
		BeanUtils.copyProperties(model, existingPedido, "id");
		return model;
	}

	@Override
	public void delete(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		Pedido existingPedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pedido not found with id: " + id));
		pedidoRepository.delete(existingPedido);
	}

	@Override
	public Pedido findById(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Pedido not found with id: " + id));
	}

	@Override
	public List<Pedido> findAll() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos != null ? pedidos : new ArrayList<>();
	}

}
