package br.edu.utfpr.paranazom.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.Employee;
import br.edu.utfpr.paranazom.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee update(String employee_id, Employee employee) {
		Employee employeeSave = findEmployeeByCode(employee_id);
		
		BeanUtils.copyProperties(employee, employeeSave, "employee_id"); // Copia os valores dos atributos de pessoa para pessoaSalva, exceto codigo
		//pessoa.setCodigo(codigo);
		
		return employeeRepository.save(employeeSave);
	}
	
	public Employee findEmployeeByCode(String employee_id) {
		Optional<Employee> employeeSaveOpt = employeeRepository.findById(employee_id);
		
		if (!employeeSaveOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Employee employeeSave = employeeSaveOpt.get();
		
		
		return employeeSave;
	}
	
}