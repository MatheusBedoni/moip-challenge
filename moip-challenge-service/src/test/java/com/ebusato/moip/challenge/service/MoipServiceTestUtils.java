package com.ebusato.moip.challenge.service;

import java.time.LocalDate;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardBrand;
import com.ebusato.moip.challenge.service.model.CreditCardDetails;

public final class MoipServiceTestUtils {
	
	private MoipServiceTestUtils() { }
	
	public static Customer newCustomer() {		
		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setEmail("john@doe.com");
		customer.setCpf("00000000191");		
		return customer;
	}
		
	public static CreditCardDetails newCreditCardDetails() {
		CreditCardDetails ccDetails = new CreditCardDetails();
		ccDetails.setBrand(CreditCardBrand.MASTERCARD);
		ccDetails.setCvv("666");
		ccDetails.setExpiration(LocalDate.now());
		ccDetails.setHolder("James Smith");
		ccDetails.setNumber("66667777788889999");
		return ccDetails;
	}

}
