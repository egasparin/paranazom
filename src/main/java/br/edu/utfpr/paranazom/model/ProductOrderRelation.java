package br.edu.utfpr.paranazom.model;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private Product product;

	@NotNull
	private Order order;

	@NotNull
	private Long amount;
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(option_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Options other = (Options) obj;
		return Objects.equals(option_id, other.option_id);
	}
}
	