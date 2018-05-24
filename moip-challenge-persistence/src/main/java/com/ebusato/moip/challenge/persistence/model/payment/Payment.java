package com.ebusato.moip.challenge.persistence.model.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

@Document
public abstract class Payment implements Comparable<Payment>, Serializable {

	private static final long serialVersionUID = 8287940572560195049L;
	
	@Id
	protected String id;
	
	@DBRef
	protected Customer customer;
	
	protected PaymentType type;
	protected PaymentStatus status;
	
	protected BigDecimal amount;
	
	public Payment(Customer customer, BigDecimal amount) {
		this.customer = customer;
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PaymentType getType() {
		return type;
	}
	
	public void setType(PaymentType type) {
		this.type = type;
	}
	
	public PaymentStatus getStatus() {
		return status;
	}
	
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
					.add("type", type)
						.add("status", status)
							.add("amount", amount)
								.add("customer", customer)
									.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, type, status, amount, customer);
	}
	
	@Override
	public boolean equals(Object other) {
		return Objects.equals(this, other);
	}

	@Override
	public int compareTo(Payment that) {
		return ComparisonChain.start().compare(this,  that).result();
	}
}