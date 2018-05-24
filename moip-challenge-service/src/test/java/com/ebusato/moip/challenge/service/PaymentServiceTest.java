package com.ebusato.moip.challenge.service;

import static com.ebusato.moip.challenge.service.MoipServiceTestUtils.newCreditCardDetails;
import static com.ebusato.moip.challenge.service.MoipServiceTestUtils.newCustomer;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;

import java.math.BigDecimal;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentStatus;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.ebusato.moip.challenge.persistence.model.payment.boleto.BoletoPayment;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;
import com.ebusato.moip.challenge.persistence.repository.PaymentRepository;
import com.ebusato.moip.challenge.service.model.CreditCardDetails;
import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {
	
	@Mock
	private PaymentRepository paymentRepository;
	
	@Mock
	private CustomerService customerService;
	
	@Mock
	private AcquirerServiceMock acquirerService;	
	
	@InjectMocks
	private PaymentServiceImpl paymentService;
	
	@Before
	public void init() {
		Mockito.when(customerService.createIfAbsent(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);		
		Mockito.when(paymentRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
		Mockito.when(acquirerService.process(Mockito.any())).thenCallRealMethod();
	}
	
	@Test
	public void testPaymentTypes() {
		//given
		List<PaymentType> expectedTypes = Lists.newArrayList(PaymentType.BOLETO, PaymentType.CREDIT_CARD);
		
		//when
		List<PaymentType> paymentTypes = paymentService.getPaymentTypes();
		
		//then
		MatcherAssert.assertThat(paymentTypes, containsInAnyOrder(expectedTypes.toArray()));
	}
	
	@Test
	public void testBoletoPayment() {
		//given
		BigDecimal amount = BigDecimal.valueOf(20D);
		Customer customer = newCustomer();		
		
		//when
		BoletoPayment boletoPayment = paymentService.processBoletoPayment(amount, customer);
		
		//then		
		MatcherAssert.assertThat(boletoPayment.getNumber(), not(Matchers.isEmptyString()));
	}
	
	@Test
	public void testCreditCardPayment() {
		//given
		BigDecimal amount = BigDecimal.valueOf(20D);
		Customer customer = newCustomer();		
		CreditCardDetails ccDetails = newCreditCardDetails();
		List<PaymentStatus> expectedStatus = Lists.newArrayList(PaymentStatus.APPROVED, PaymentStatus.DECLINED);
		
		//when
		CreditCardPayment ccPayment = paymentService.processCreditCardPayment(amount, customer, ccDetails);
		
		//then
		MatcherAssert.assertThat(expectedStatus, Matchers.hasItem(ccPayment.getStatus()));
	}
}