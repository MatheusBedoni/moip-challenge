package com.ebusato.moip.challenge.persistence.model.payment.boleto;

import java.math.BigDecimal;
import java.util.Objects;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.Payment;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

public class BoletoPayment extends Payment {

	private static final long serialVersionUID = 565365668541167957L;
	
	private String number;
		
	public BoletoPayment(Customer customer, BigDecimal amount) {
		super(customer, amount);
		this.type = PaymentType.BOLETO;
	}
		
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)				
				.add("id", id)
					.add("type", type)
						.add("status", status)
							.add("amount", amount)
								.add("number", number)
									.add("customer", customer)
										.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, type, status, amount, number, customer);
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