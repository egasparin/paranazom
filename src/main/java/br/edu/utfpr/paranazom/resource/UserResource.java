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

import br.edu.utfpr.paranazom.model.User;
import br.edu.utfpr.paranazom.repository.UserRepository;
import br.edu.utfpr.paranazom.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('READ_USER')")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('WRITE_USER')")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> create(@RequestBody User user, HttpServletResponse response) {
		User userSave = userRepository.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{user_id}").buildAndExpand(userSave.getUser_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(userSave);
	}
	
	@GetMapping("/{user_id}")
	@PreAuthorize("hasAuthority('READ_USER')")
	public ResponseEntity<?> getByCode(@PathVariable String user_id) {
		Optional<User> user = userRepository.findById(user_id);
		return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{user_id}")
	@PreAuthorize("hasAuthority('WRITE_USER')")
	@ResponseStatus(HttpStatus.NO_CONTENT) // código 204: deu certo, porém não tenho nada para retornar
	public void delete(@PathVariable String user_id) {
		this.userRepository.deleteById(user_id);
	}
	
	@PutMapping("/{user_id}")
	@PreAuthorize("hasAuthority('WRITE_USER')")
	public ResponseEntity<User> update(@PathVariable String user_id, @RequestBody User user) {
		User userSave = userService.update(user_id, user);
		return ResponseEntity.ok(userSave);
	}
	
	
}
