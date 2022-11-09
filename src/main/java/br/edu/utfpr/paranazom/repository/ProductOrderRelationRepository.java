package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.ProductOrderRelation;

public interface ProductOrderRelationRepository extends JpaRepository<ProductOrderRelation, String> {

}
