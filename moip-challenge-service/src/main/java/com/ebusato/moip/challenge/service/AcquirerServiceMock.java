package com.ebusato.moip.challenge.service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.ebusato.moip.challenge.persistence.model.payment.PaymentStatus;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;
import com.ebusato.moip.challenge.service.model.AcquirerResponse;

@Service
public class AcquirerServiceMock implements AcquirerService {

	@Override
	public AcquirerResponse process(CreditCardPayment payment) {		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		boolean success = random.nextBoolean();
		String transactionId = UUID.randomUUID().toString();
		return success ? new AcquirerResponse(PaymentStatus.APPROVED, transactionId) : new AcquirerResponse(PaymentStatus.DECLINED, transactionId);
	}
}
