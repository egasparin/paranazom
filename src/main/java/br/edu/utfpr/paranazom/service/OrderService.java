package br.edu.utfpr.paranazom.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.Order;
import br.edu.utfpr.paranazom.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	public Order update(String order_id, Order order) {
		Order orderSave = findOrderByCode(order_id);
		
		BeanUtils.copyProperties(order, orderSave, "order_id"); // Copia os valores dos atributos de pessoa para pessoaSalva, exceto codigo
		//pessoa.setCodigo(codigo);
		
		return orderRepository.save(orderSave);
	}
	
	public Order findOrderByCode(String order_id) {
		Optional<Order> orderSaveOpt = orderRepository.findById(order_id);
		
		if (!orderSaveOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Order orderSave = orderSaveOpt.get();
		
		
		return orderSave;
	}
	
	public void updateDraft(String order_id) {
		Order orderSalva = findOrderByCode(order_id);
		orderSalva.setDraft(!orderSalva.isDraft());
		this.orderRepository.save(orderSalva);
	}
	
	public void updatePaid(String order_id) {
		Order orderSalva = findOrderByCode(order_id);
		orderSalva.setPaid(!orderSalva.isPaid());
		this.orderRepository.save(orderSalva);
	}
	
	public void updatewithdrawn(String order_id) {
		Order orderSalva = findOrderByCode(order_id);
		orderSalva.setWithdrawn(!orderSalva.isWithdrawn());
		this.orderRepository.save(orderSalva);
	}
	
}