package br.edu.utfpr.paranazom.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.paranazom.model.ProductOrderRelation;
import br.edu.utfpr.paranazom.repository.ProductOrderRelationRepository;
import br.edu.utfpr.paranazom.service.ProductOrderRelationService;


@RestController
@RequestMapping("/ordersProducts")
public class ProductOrderRelationResource {
	
	@Autowired
	private ProductOrderRelationRepository productOrderRepository;
	
	@Autowired
	private ProductOrderRelationService productOrderService;
	
	@GetMapping
	public List<ProductOrderRelation> list() {
		return productOrderRepository.findAll();
	}
	
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProductOrderRelation> create(@RequestBody ProductOrderRelation po, HttpServletResponse response) {
		ProductOrderRelation poSave = productOrderService.addProductOrder(po);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{product_order_id}").buildAndExpand(poSave.getProduct_order_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(poSave);
	}
	
}
