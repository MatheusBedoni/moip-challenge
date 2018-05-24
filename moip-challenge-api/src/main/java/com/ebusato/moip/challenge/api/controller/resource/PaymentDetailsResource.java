package com.ebusato.moip.challenge.api.controller.resource;

import java.time.LocalDate;
import java.time.YearMonth;

import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardBrand;
import com.ebusato.moip.challenge.service.model.CreditCardDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentDetailsResource {
	
	private PaymentType type;
	private CreditCardBrand brand;
	private String holder;
	private String number;
	private String cvv;	
	
	@JsonFormat(pattern = "yyyy-MM")
	private YearMonth expiration;
	
	public PaymentType getType() {
		return type;
	}
	public void setType(PaymentType type) {
		this.type = type;
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
	public YearMonth getExpiration() {
		return expiration;
	}
	public void setExpiration(YearMonth expiration) {
		this.expiration = expiration;
	}
	public CreditCardDetails toCreditCardDetails() {
		CreditCardDetails details = new CreditCardDetails();
		details.setBrand(this.brand);
		details.setCvv(this.cvv);
		details.setHolder(this.holder);
		details.setNumber(this.number);
		details.setExpiration(LocalDate.of(this.getExpiration().getYear(), this.getExpiration().getMonth(), 1));
		return details;
	}
}