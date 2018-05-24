package com.ebusato.moip.challenge.api.controller.resource;

import org.springframework.util.Assert;

import com.ebusato.moip.challenge.persistence.model.payment.PaymentStatus;
import com.ebusato.moip.challenge.persistence.model.payment.boleto.BoletoPayment;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.MoreObjects;

public class PaymentResponseResource {
	
	private String id;

	@JsonInclude(Include.NON_NULL)
	private String number;
	
	private PaymentStatus status;
	
	public PaymentResponseResource(BoletoPayment response) {		
		Assert.notNull(response, "response is null!");
		this.id = response.getId();
		this.number = response.getNumber();
		this.status = response.getStatus();
	}
	
	public PaymentResponseResource(CreditCardPayment response) {
		Assert.notNull(response, "response is null!");
		this.id = response.getId();		
		this.status = response.getStatus();
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
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
					.add("number", number)
						.add("status", status)
							.toString();
	}
}