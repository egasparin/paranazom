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
@Table(name = "tb_options_has_tb_product")
public class OptionProductRelation {
	
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	
	// Definição dos atributos;
	@NotNull
	private String optionProdRelation_id;
	
	@ManyToOne
    @JoinColumn(name="product_id", nullable=false)
	private Product product;

	@ManyToOne
    @JoinColumn(name="option_id", nullable=false)
	private Options option;

	@NotNull
	private String description;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Options getOption() {
		return option;
	}

	public void setOption(Options option) {
		this.option = option;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getOptionProdRelation_id() {
		return optionProdRelation_id;
	}

	public void setOptionProdRelation_id(String optionProdRelation_id) {
		this.optionProdRelation_id = optionProdRelation_id;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(optionProdRelation_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OptionProductRelation other = (OptionProductRelation) obj;
		return Objects.equals(optionProdRelation_id, other.optionProdRelation_id);
	}


}
	