package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
