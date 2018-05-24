package com.ebusato.moip.challenge.api.controller.resource;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ebusato.moip.challenge.api.controller.validation.PaymentDetailsConstraint;
import com.google.common.base.MoreObjects;

public class PaymentRequestResource {
	
	@NotNull
	@Digits(integer=10, fraction=2)
	@Min(value = 1)
	private BigDecimal amount;
	
	@NotNull
	@NotBlank
	private String clientId;
	
	@Valid
	@NotNull
	private CustomerResource customer;
	
	@PaymentDetailsConstraint
	private PaymentDetailsResource details;
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public CustomerResource getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerResource customer) {
		this.customer = customer;
	}	
	public PaymentDetailsResource getDetails() {
		return details;
	}
	public void setDetails(PaymentDetailsResource details) {
		this.details = details;
	}	
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("amount", amount)
					.add("clientId", clientId)
						.add("customer", customer)
							.add("details", details)
								.toString();
	}
}