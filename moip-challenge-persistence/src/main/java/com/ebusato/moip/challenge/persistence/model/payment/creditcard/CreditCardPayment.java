package com.ebusato.moip.challenge.persistence.model.payment.creditcard;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.Payment;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

public class CreditCardPayment extends Payment {

	private static final long serialVersionUID = -1914142869141727940L;

	private CreditCardBrand brand;
	
	private String holder;
	private String number;
	private String cvv;
	
	private LocalDate expiration;
	
	public CreditCardPayment(Customer customer, BigDecimal amount) {
		super(customer, amount);
		this.type = PaymentType.CREDIT_CARD;
	}

	public CreditCardBrand getBrand() {
		return brand;
	}

	public void setBrand(CreditCardBrand brand) {
		this.brand = brand;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)				
				.add("id", id)
					.add("type", type)
						.add("status", status)
							.add("amount", amount)
								.add("brand", brand)
									.add("holder", holder)
										.add("number", number)
											.add("cvv", cvv)
												.add("customer", customer)
													.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, type, status, amount, brand, holder, number, cvv, customer);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
	        return true;
	    }
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    CreditCardPayment other = CreditCardPayment.class.cast(obj);
	    
		return new EqualsBuilder()
				.append(id, other.id)
					.append(type, other.type)
						.append(status, other.status)
							.append(amount, other.amount)
								.append(customer, other.customer)
									.append(brand, other.brand)
										.append(holder, other.holder)
											.append(number, other.number)
												.append(cvv, other.cvv)
													.append(expiration, other.expiration)
														.isEquals();
	}

	@Override
	public int compareTo(Payment that) {
		return ComparisonChain.start().compare(this,  that).result();
	}
}