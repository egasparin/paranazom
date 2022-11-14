package br.edu.utfpr.paranazom.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.utfpr.paranazom.model.Role;
import br.edu.utfpr.paranazom.repository.RoleRepository;

@RestController
@RequestMapping("/roles")
public class RoleResource {
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('READ_ROLE') and #oauth2.hasScope('write')")
	public List<Role> list() {
		return roleRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('WRITE_ROLE') and #oauth2.hasScope('write')")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Role> create(@RequestBody Role role, HttpServletResponse response) {
		Role roleSave = roleRepository.save(role);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{role_id}").buildAndExpand(roleSave.getRole_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(roleSave);
	}
	
	@GetMapping("/{role_id}")
	@PreAuthorize("hasAuthority('WRITE_ROLE') and #oauth2.hasScope('write')")
	public ResponseEntity<?> getByCode(@PathVariable String role_id) {
		Optional<Role> role = roleRepository.findById(role_id);
		return role.isPresent() ? ResponseEntity.ok(role) : ResponseEntity.notFound().build();
	}
}
