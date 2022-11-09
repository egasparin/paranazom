package br.edu.utfpr.paranazom.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.paranazom.model.Order;
import br.edu.utfpr.paranazom.repository.OrderRepository;
import br.edu.utfpr.paranazom.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public List<Order> list() {
		return orderRepository.findAll();
	}
	
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Order> create(@RequestBody Order order, HttpServletResponse response) {
		Order orderSave = orderRepository.save(order);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{order_id}").buildAndExpand(orderSave.getOrder_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(orderSave);
	}
	
	@GetMapping("/{order_id}")
	public ResponseEntity<?> getByCode(@PathVariable String order_id) {
		Optional<Order> order = orderRepository.findById(order_id);
		return order.isPresent() ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{order_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // código 204: deu certo, porém não tenho nada para retornar
	public void delete(@PathVariable String order_id) {
		this.orderRepository.deleteById(order_id);
	}
	
	@PutMapping("/{order_id}")
	public ResponseEntity<Order> update(@PathVariable String order_id, @RequestBody Order order) {
		Order orderSave = orderService.update(order_id, order);
		return ResponseEntity.ok(orderSave);
	}

	@PutMapping("/{order_id}/draft")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDraft(@PathVariable String order_id, @RequestBody boolean draft) {
		orderService.updateDraft(order_id, draft);
	}
	
}
