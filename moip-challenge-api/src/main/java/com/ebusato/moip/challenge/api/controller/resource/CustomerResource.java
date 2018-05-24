package com.ebusato.moip.challenge.api.controller.resource;

import javax.validation.constraints.NotBlank;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.google.common.base.MoreObjects;

public class CustomerResource {
	
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@NotBlank
	private String cpf;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("name", name)
					.add("email", email)
						.add("cpf", cpf)
							.toString();
	}
	
	public Customer toCustomer() {
		Customer customer = new Customer();
		customer.setCpf(this.cpf);
		customer.setEmail(this.email);
		customer.setName(this.name);
		return customer;
	}
}
