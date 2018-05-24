package com.ebusato.moip.challenge.persistence.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

public class Customer implements Comparable<Customer>, Serializable {

	private static final long serialVersionUID = -3123451473449899508L;

	@Id
	private String id;
	
	private String name;
	private String email;
	private String cpf;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
				.add("id", id)
					.add("name", name)
						.add("email", email)
							.add("cpf", cpf)
								.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, email, cpf);
	}
	
	@Override
	public boolean equals(Object other) {
		return Objects.equals(this, other);
	}

	@Override
	public int compareTo(Customer that) {
		return ComparisonChain.start().compare(this,  that).result();
	}
}