package br.edu.utfpr.paranazom.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import br.edu.utfpr.paranazom.model.Category;
import br.edu.utfpr.paranazom.repository.CategoryRepository;
import br.edu.utfpr.paranazom.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('READ_CATEGORY') and #oauth2.hasScope('write')")
	public List<Category> list() {
		return categoryRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('WRITE_CATEGORY') and #oauth2.hasScope('write')")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
		Category categorySave = categoryRepository.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{category_id}").buildAndExpand(categorySave.getCategory_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categorySave);
	}
	
	@GetMapping("/{category_id}")
	@PreAuthorize("hasAuthority('READ_CATEGORY') and #oauth2.hasScope('write')")
	public ResponseEntity<?> getByCode(@PathVariable String category_id) {
		Optional<Category> category = categoryRepository.findById(category_id);
		return category.isPresent() ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{category_id}")
	@PreAuthorize("hasAuthority('WRITE_CATEGORY') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT) // código 204: deu certo, porém não tenho nada para retornar
	public void delete(@PathVariable String category_id) {
		this.categoryRepository.deleteById(category_id);
	}
	
	@PutMapping("/{category_id}")
	@PreAuthorize("hasAuthority('WRITE_CATEGORY') and #oauth2.hasScope('write')")
	public ResponseEntity<Category> update(@PathVariable String category_id, @RequestBody Category category) {
		Category categorySave = categoryService.update(category_id, category);
		return ResponseEntity.ok(categorySave);
	}
	
}
