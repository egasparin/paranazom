package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
