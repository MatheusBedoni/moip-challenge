package com.ebusato.moip.challenge.service;

import java.math.BigDecimal;
import java.util.List;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.ebusato.moip.challenge.persistence.model.payment.boleto.BoletoPayment;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;
import com.ebusato.moip.challenge.service.model.CreditCardDetails;

/**
 * Payment service layer.
 * @author Elias.
 *
 */
public interface PaymentService {

	/**
	 * Returns a list of accepted payment types.
	 * @return.
	 */
	List<PaymentType> getPaymentTypes();

	/**
	 * Process a new boleto payment.
	 * @param amount transaction amount.
	 * @param customer transaction customer.
	 * @return boleto with generated number. 
	 */
	BoletoPayment processBoletoPayment(BigDecimal amount, Customer customer);

	/**
	 * Process a new credit card payment.
	 * @param amount transaction amount.
	 * @param customer transaction customer.
	 * @param ccDetails credit card details.
	 * @return credit card payment with status.
	 */
	CreditCardPayment processCreditCardPayment(BigDecimal amount, Customer customer, CreditCardDetails ccDetails);

}
