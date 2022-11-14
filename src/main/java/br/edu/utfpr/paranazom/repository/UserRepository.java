package br.edu.utfpr.paranazom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	public Optional<User> findByEmail(String email);
}