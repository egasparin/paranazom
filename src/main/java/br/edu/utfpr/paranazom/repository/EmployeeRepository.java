package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}

