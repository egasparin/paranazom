package br.edu.utfpr.paranazom.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_order")
public class Order {
	
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private String order_id;
	
	@NotNull
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean draft = true;
	
	@NotNull
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean paid;
	
	@NotNull
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean withdrawn;
	
	@NotNull
	@Size(min = 3, max = 254)
	private String description;
	
	private float discount;
	
	@JsonIgnoreProperties("user")
	@ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
	private Employee employee;
	
	@ManyToOne
    @JoinColumn(name="client_id", nullable=false)
	private Client client;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isWithdrawn() {
		return withdrawn;
	}

	public void setWithdrawn(boolean withdraw) {
		this.withdrawn = withdraw;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(order_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(order_id, other.order_id);
	}
	
	
	
}