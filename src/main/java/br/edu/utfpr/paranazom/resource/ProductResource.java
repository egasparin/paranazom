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

import br.edu.utfpr.paranazom.model.Product;
import br.edu.utfpr.paranazom.repository.ProductRepository;
import br.edu.utfpr.paranazom.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> list() {
		return productRepository.findAll();
	}
	
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> create(@RequestBody Product product, HttpServletResponse response) {
		Product productSave = productRepository.save(product);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{product_id}").buildAndExpand(productSave.getProduct_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(productSave);
	}
	
	@GetMapping("/{product_id}")
	public ResponseEntity<?> getByCode(@PathVariable String product_id) {
		Optional<Product> product = productRepository.findById(product_id);
		return product.isPresent() ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{product_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // código 204: deu certo, porém não tenho nada para retornar
	public void delete(@PathVariable String product_id) {
		this.productRepository.deleteById(product_id);
	}
	
	@PutMapping("/{product_id}")
	public ResponseEntity<Product> update(@PathVariable String product_id, @RequestBody Product product) {
		Product productSave = productService.update(product_id, product);
		return ResponseEntity.ok(productSave);
	}	
}
