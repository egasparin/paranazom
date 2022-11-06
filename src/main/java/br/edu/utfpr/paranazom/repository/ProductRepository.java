package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.Product;
import br.edu.utfpr.paranazom.repository.search.ProductRepositoryQuery;

public interface ProductRepository extends JpaRepository<Product, String>, ProductRepositoryQuery{

}
