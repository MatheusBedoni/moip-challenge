package com.ebusato.moip.challenge.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebusato.moip.challenge.api.controller.resource.PaymentRequestResource;
import com.ebusato.moip.challenge.api.controller.resource.PaymentResponseResource;
import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.Payment;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.ebusato.moip.challenge.persistence.model.payment.boleto.BoletoPayment;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;
import com.ebusato.moip.challenge.service.PaymentService;

@RestController
@RequestMapping(PaymentController.PATH)
public class PaymentController {
	
	public static final String PATH = "payments";
	
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping
	public PaymentResponseResource post(@Valid @RequestBody PaymentRequestResource paymentResource) {
		
		PaymentType type = paymentResource.getDetails().getType();
		Customer customer = paymentResource.getCustomer().toCustomer();
		
		switch (type) {
			case BOLETO:
				BoletoPayment boletoResponse = paymentService.processBoletoPayment(paymentResource.getAmount(), customer);
				return new PaymentResponseResource(boletoResponse);
			case CREDIT_CARD:
				CreditCardPayment ccResponse = paymentService.processCreditCardPayment(paymentResource.getAmount(), customer, paymentResource.getDetails().toCreditCardDetails());
				return new PaymentResponseResource(ccResponse);
			default:	
				throw new UnsupportedOperationException("unsupported payment type");			
		}
	}
	
	@GetMapping("/{paymentId}")
	public Payment get(@PathVariable String paymentId) {
		return paymentService.getDetails(paymentId);
	}
	
}