package br.edu.utfpr.paranazom.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_product_has_tb_order")
public class ProductOrderRelation {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long product_order_id;
	
	@ManyToOne
    @JoinColumn(name="product_id", nullable=false)
	private Product product;

	@JsonIgnoreProperties("products_order")
	@ManyToOne
    @JoinColumn(name="order_id", nullable=false)
	private Order order;
	
	@NotNull
	private Long amount;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	public Long getProduct_order_id() {
		return product_order_id;
	}

	public void setProduct_order_id(Long product_order_id) {
		this.product_order_id = product_order_id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOrderRelation other = (ProductOrderRelation) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	

}
