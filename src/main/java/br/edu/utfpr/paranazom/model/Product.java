package br.edu.utfpr.paranazom.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_product")
public class Product {
	
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	
	// Declaração dos atributos;
	
	private String product_id;
	
	@NotNull
	@Size(min = 3, max = 200)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 36)
	private String tags;
	
	@NotNull
	private double price;
	
	@NotNull
	private long amount;
	
	private String image_url;

	// temos que definir o tipo de relacionamento;
	@ManyToOne
    @JoinColumn(name="category_id", nullable=false)
	private Category category;
	
	@OneToMany(mappedBy="product")
	private List<OptionProductRelation> optionProductRelation;
	
	@OneToMany(mappedBy="product")
	private List<ProductOrderRelation> productOrderRelation;
	
	// Declaração do getters e setters;
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	public List<OptionProductRelation> getOptionProductRelation() {
		return optionProductRelation;
	}

	public void setOptionProductRelation(List<OptionProductRelation> optionProductRelation) {
		this.optionProductRelation = optionProductRelation;
	}

	public List<ProductOrderRelation> getProductOrderRelation() {
		return productOrderRelation;
	}

	public void setProductOrderRelation(List<ProductOrderRelation> productOrderRelation) {
		this.productOrderRelation = productOrderRelation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProduct_id());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(getProduct_id(), other.getProduct_id());
	}
	
}
