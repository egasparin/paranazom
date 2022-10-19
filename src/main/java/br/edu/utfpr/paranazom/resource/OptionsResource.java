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

import br.edu.utfpr.paranazom.model.Options;
import br.edu.utfpr.paranazom.repository.OptionsRepository;



@RestController
@RequestMapping("/options")
public class OptionsResource {
	@Autowired
	private OptionsRepository optionsRepository;
	
	@GetMapping
	public List<Options> list() {
		return optionsRepository.findAll();
	}
	
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Options> create(@RequestBody Options options, HttpServletResponse response) {
		Options optionsSave = optionsRepository.save(options);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{option_id}").buildAndExpand(optionsSave.getOption_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(optionsSave);
	}
	
	@GetMapping("/{options_id}")
	public ResponseEntity<?> getByCode(@PathVariable String option_id) {
		Optional<Options> options = optionsRepository.findById(option_id);
		return options.isPresent() ? ResponseEntity.ok(options) : ResponseEntity.notFound().build();
	}
}
