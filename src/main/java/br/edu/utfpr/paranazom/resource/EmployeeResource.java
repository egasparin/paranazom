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

import br.edu.utfpr.paranazom.model.Employee;
import br.edu.utfpr.paranazom.repository.EmployeeRepository;
import br.edu.utfpr.paranazom.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('READ_EMPLOYEE')")
	public List<Employee> list() {
		return employeeRepository.findAll();
	}
	
	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('WRITE_EMPLOYEE')")
	public ResponseEntity<Employee> create(@RequestBody Employee employee, HttpServletResponse response) {
		Employee employeeSave = employeeRepository.save(employee);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{employee_id}").buildAndExpand(employeeSave.getEmployee_id()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(employeeSave);
	}
	
	@GetMapping("/{employee_id}")
	@PreAuthorize("hasAuthority('READ_EMPLOYEE')")
	public ResponseEntity<?> getByCode(@PathVariable String employee_id) {
		Optional<Employee> employee = employeeRepository.findById(employee_id);
		return employee.isPresent() ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{employee_id}")
	@PreAuthorize("hasAuthority('WRITE_EMPLOYEE')")
	@ResponseStatus(HttpStatus.NO_CONTENT) // código 204: deu certo, porém não tenho nada para retornar
	public void delete(@PathVariable String employee_id) {
		this.employeeRepository.deleteById(employee_id);
	}
	
	@PutMapping("/{employee_id}")
	@PreAuthorize("hasAuthority('WRITE_EMPLOYEE')")
	public ResponseEntity<Employee> update(@PathVariable String employee_id, @RequestBody Employee employee) {
		Employee employeeSave = employeeService.update(employee_id, employee);
		return ResponseEntity.ok(employeeSave);
	}
	
	
}
