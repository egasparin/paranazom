package br.edu.utfpr.paranazom.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.paranazom.model.Category;
import br.edu.utfpr.paranazom.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public List<Category> list() {
		return categoryRepository.findAll();
	}
	
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
		Category categorySave = categoryRepository.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{category_id}").buildAndExpand(categorySave.getCategory_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categorySave);
	}
	
	@GetMapping("/{category_id}")
	public ResponseEntity<?> getByCode(@PathVariable String category_id) {
		Optional<Category> category = categoryRepository.findById(category_id);
		return category.isPresent() ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
	}
	
}
