package com.ebusato.moip.challenge.service.model;

import java.time.LocalDate;

import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardBrand;
import com.google.common.base.MoreObjects;

public class CreditCardDetails {
	
	private CreditCardBrand brand;
	private String holder;
	private String number;
	private String cvv;	
	private LocalDate expiration;
	
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
				.add("brand",  brand)
					.add("holder", holder)
						.add("number", number)
							.add("cvv", cvv)
								.add("expiration", expiration)
									.toString();		
	}	
}