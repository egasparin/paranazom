package br.edu.utfpr.paranazom.model;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_product_has_tb_order")
public class ProductOrderRelation {
	
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	
	// Definição dos atributos;
	@NotNull
	private String prodOrderRelation_id;
	
	@ManyToOne
    @JoinColumn(name="product_id", nullable=false)
	private Product product;

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

	public String getProdOrderRelation_id() {
		return prodOrderRelation_id;
	}

	public void setProdOrderRelation_id(String prodOrderRelation_id) {
		this.prodOrderRelation_id = prodOrderRelation_id;
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
		return Objects.hash(prodOrderRelation_id);
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
		return Objects.equals(prodOrderRelation_id, other.prodOrderRelation_id);
	}
}
	