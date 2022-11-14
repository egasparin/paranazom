package br.edu.utfpr.paranazom.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
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

import br.edu.utfpr.paranazom.model.Client;
import br.edu.utfpr.paranazom.repository.ClientRepository;
//import br.edu.utfpr.paranazom.repository.filter.ClientFilter;
import br.edu.utfpr.paranazom.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientService clientService;
	
	// Tive que comentar, pois estava dando ambiguidade entre o list() e o pageList()
	
	@GetMapping
	@PreAuthorize("hasAuthority('READ_CLIENT') and #oauth2.hasScope('write')")
	public List<Client> list() {
		return clientRepository.findAll();
	}

	/* ACREDITO NAO VALER A PENA FAZER A PAGINACAO DOS CLIENTES
	
	@GetMapping
	public Page<Client> pageFilter(ClientFilter clientFilter, Pageable pageable){
		return clientRepository.pageFilter(clientFilter, pageable);
	}
	*/
		
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('WRITE_CLIENT') and #oauth2.hasScope('write')")
	public ResponseEntity<Client> create(@RequestBody Client client, HttpServletResponse response) {
		Client clientSave = clientRepository.save(client);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{client_id}").buildAndExpand(clientSave.getClient_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(clientSave);
	}
//	

	@GetMapping("/{client_id}")
	@PreAuthorize("hasAuthority('READ_CLIENT') and #oauth2.hasScope('write')")
	public ResponseEntity<?> getByCode(@PathVariable String client_id) {
		Optional<Client> client = clientRepository.findById(client_id);
		return client.isPresent() ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{client_id}")
	@PreAuthorize("hasAuthority('WRITE_CLIENT') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT) // código 204: deu certo, porém não tenho nada para retornar
	public void delete(@PathVariable String client_id) {
		this.clientRepository.deleteById(client_id);
	}
	
	@PutMapping("/{client_id}")
	@PreAuthorize("hasAuthority('WRITE_CLIENT') and #oauth2.hasScope('write')")
	public ResponseEntity<Client> update(@PathVariable String client_id, @RequestBody Client client) {
		Client clientSave = clientService.update(client_id, client);
		return ResponseEntity.ok(clientSave);
	}
	
	
}