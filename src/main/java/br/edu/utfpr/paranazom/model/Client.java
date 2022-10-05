package br.edu.utfpr.paranazom.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	// Declaração dos atributos;
	
	private String client_id;
	
	@NotNull
	@Size(min = 3, max = 254)
	private String name;
	
	@NotNull
	@Column(length = 11)
	private String cpf;
	

	private String rg;
	private String email_address;
	private String phone_number;
	private String address;

	// Declaração dos Sets e Gets
	public String getClientId() {
		return client_id;
	}

	public void setClientId(String client_id) {
		this.client_id = client_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	@SuppressWarnings("unused")
	private String getEmailAddress() {
		return email_address;
	}

	@SuppressWarnings("unused")
	private void setEmailAddress(String email_address) {
		this.email_address = email_address;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
