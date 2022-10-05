package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.Client;

public interface ClientRepository extends JpaRepository<Client, String>{

}
