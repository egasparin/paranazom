package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}