package com.ebusato.moip.challenge.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.Payment;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentStatus;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.ebusato.moip.challenge.persistence.model.payment.boleto.BoletoPayment;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;
import com.ebusato.moip.challenge.persistence.repository.PaymentRepository;
import com.ebusato.moip.challenge.service.AcquirerService;
import com.ebusato.moip.challenge.service.CustomerService;
import com.ebusato.moip.challenge.service.PaymentService;
import com.ebusato.moip.challenge.service.model.AcquirerResponse;
import com.ebusato.moip.challenge.service.model.CreditCardDetails;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private PaymentRepository paymentRepository;
	private CustomerService customerService;
	private AcquirerService acquirerService;
	
	@Autowired
	public PaymentServiceImpl(CustomerService customerService, AcquirerService acquirerService, PaymentRepository paymentRepository) {
		this.customerService = customerService;
		this.acquirerService = acquirerService;
		this.paymentRepository = paymentRepository;
	}
	
	@Override
	public List<PaymentType> getPaymentTypes() {
		return Stream.of(PaymentType.values()).collect(Collectors.toList());
	}
	
	@Override
	public BoletoPayment processBoletoPayment(BigDecimal amount, Customer customer) {	
		Customer customerFound = this.customerService.createIfAbsent(customer);
		
		BoletoPayment boletoPayment = new BoletoPayment(customerFound, amount);
		boletoPayment.setNumber(UUID.randomUUID().toString());
		boletoPayment.setStatus(PaymentStatus.NEW);
		
		BoletoPayment saved = paymentRepository.save(boletoPayment);
		return saved;
	}

	@Override
	public CreditCardPayment processCreditCardPayment(BigDecimal amount, Customer customer, CreditCardDetails ccDetails) {
		Customer customerFound = this.customerService.createIfAbsent(customer);
		
		CreditCardPayment ccPayment = new CreditCardPayment(customerFound, amount);
		ccPayment.setBrand(ccDetails.getBrand());
		ccPayment.setCvv(ccDetails.getCvv());
		ccPayment.setExpiration(ccDetails.getExpiration());
		ccPayment.setHolder(ccDetails.getHolder());
		ccPayment.setNumber(ccDetails.getNumber());
		ccPayment.setStatus(PaymentStatus.PROCESSING);
		
		CreditCardPayment saved = paymentRepository.save(ccPayment);
		
		AcquirerResponse response = acquirerService.process(saved);
		saved.setStatus(response.getStatus());
		
		CreditCardPayment updated = paymentRepository.save(saved);
		return updated;
	}
	
	@Override
	public Payment getDetails(String paymentId) {
		Optional<Payment> found = this.paymentRepository.findById(paymentId);
		return found.get();
	}
}