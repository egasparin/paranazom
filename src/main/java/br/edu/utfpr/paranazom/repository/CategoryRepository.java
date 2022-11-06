package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}

