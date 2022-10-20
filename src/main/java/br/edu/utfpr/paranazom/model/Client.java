package br.edu.utfpr.paranazom.model;

//import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_client")
public class Client {
	
	@Id @GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	private String client_id;
	
	@NotNull
	@Size(min = 3, max = 254)
	private String name;
	
	@NotNull
	@Column(length = 11, name = "CPF")
	private String cpf;
	
	@Column(name = "RG")
	private String rg;
	private String email_address;
	private String fone_number;
	private String address;
	
//	@Nullable
//	@OneToMany(mappedBy="client")
//	private List<Order> order;

	// Declaração dos Sets e Gets

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getFone_number() {
		return fone_number;
	}

	public void setFone_number(String phone_number) {
		this.fone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public List<Order> getOrder() {
//		return order;
//	}
//
//	public void setOrder(List<Order> order) {
//		this.order = order;
//	}

	@SuppressWarnings("unused")
	private void setEmailAddress(String email_address) {
		this.email_address = email_address;
	}
	
	@SuppressWarnings("unused")
	private String getEmailAddress() {
		return email_address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(client_id, other.client_id);
	}
		
}
