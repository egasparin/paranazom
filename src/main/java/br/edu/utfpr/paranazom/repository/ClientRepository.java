package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.Client;
import br.edu.utfpr.paranazom.repository.search.ClientRepositoryQuery;

public interface ClientRepository extends JpaRepository<Client, String>, ClientRepositoryQuery{

}
