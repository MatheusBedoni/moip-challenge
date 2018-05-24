package com.ebusato.moip.challenge.service;

import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;
import com.ebusato.moip.challenge.service.model.AcquirerResponse;

/**
 * Acquirer Service layer.
 * @author Elias.
 *
 */
public interface AcquirerService {
	
	/**
	 * Calls acquirer to process a credit card payment.
	 * @param payment payment details.
	 * @return transaction id and payment status.
	 */
	AcquirerResponse process(CreditCardPayment payment);

}
