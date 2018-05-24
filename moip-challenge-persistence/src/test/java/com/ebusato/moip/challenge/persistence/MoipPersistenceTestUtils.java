package com.ebusato.moip.challenge.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.Payment;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentStatus;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.ebusato.moip.challenge.persistence.model.payment.boleto.BoletoPayment;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardBrand;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;

public final class MoipPersistenceTestUtils {
	
	private MoipPersistenceTestUtils() { }
	
	public static Customer newCustomer() {		
		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setEmail("john@doe.com");
		customer.setCpf("00000000191");		
		return customer;
	}
	
	public static Payment newPayment(PaymentType type, Customer customer) {
		
		Payment payment = null;
		
		switch (type) {
			case CREDIT_CARD:
				payment = new CreditCardPayment(customer, BigDecimal.valueOf(20));
				CreditCardPayment ccPayment = CreditCardPayment.class.cast(payment);				
				ccPayment.setBrand(CreditCardBrand.MASTERCARD);
				ccPayment.setCvv("666");
				ccPayment.setExpiration(LocalDate.now());
				ccPayment.setHolder("James Smith");
				ccPayment.setNumber("66667777788889999");
				break;
			case BOLETO:
				payment = new BoletoPayment(customer, BigDecimal.valueOf(20));
				BoletoPayment boletoPayment = BoletoPayment.class.cast(payment);				
				boletoPayment.setNumber("1234567890");
				break;		
			default:
				throw new IllegalArgumentException("invalid payment type!");
		}
		
		payment.setStatus(PaymentStatus.NEW);
		return payment;
	}
}