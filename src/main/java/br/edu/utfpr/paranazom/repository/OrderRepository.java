package br.edu.utfpr.paranazom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.paranazom.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
