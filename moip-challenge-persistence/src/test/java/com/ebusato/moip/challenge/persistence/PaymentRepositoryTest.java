package com.ebusato.moip.challenge.persistence;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.model.payment.Payment;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentStatus;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
import com.ebusato.moip.challenge.persistence.model.payment.boleto.BoletoPayment;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardBrand;
import com.ebusato.moip.challenge.persistence.model.payment.creditcard.CreditCardPayment;
import com.ebusato.moip.challenge.persistence.repository.CustomerRepository;
import com.ebusato.moip.challenge.persistence.repository.PaymentRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=MoipChallengePersistenceConfig.class)
@DataMongoTest
@TestExecutionListeners(mergeMode = MergeMode.MERGE_WITH_DEFAULTS, listeners = CustomerDataTestListener.class)
public class PaymentRepositoryTest implements ApplicationContextAware {
	
	@Autowired
	private PaymentRepository paymentRepository;
		
	private Customer customer;
	
	@Before 
	public void setUp() {
		paymentRepository.deleteAll();
	}
	
	@Test
	public void testSaveCreditCardPayment() {		
		//given
		Payment payment = this.newPayment(PaymentType.CREDIT_CARD);
		
		//when
		Payment saved = paymentRepository.save(payment);

		//then
		assertThat(saved.getType(), IsEqual.equalTo(PaymentType.CREDIT_CARD));
		assertThat(saved.getId(), Matchers.is(Matchers.notNullValue()));
	}
	
	@Test
	public void testSaveBoletoPayment() {		
		//given
		Payment payment = this.newPayment(PaymentType.BOLETO);
		
		//when
		Payment saved = paymentRepository.save(payment);
		
		//then
		assertThat(saved.getType(), IsEqual.equalTo(PaymentType.BOLETO));
		assertThat(saved.getId(), Matchers.is(Matchers.notNullValue()));
	}
	
	@Test
	public void testSaveAndRetrievePayment() {		
		//given
		Payment payment = this.newPayment(PaymentType.CREDIT_CARD);
		
		//when
		Payment saved = paymentRepository.save(payment);
		
		//then
		assertThat(paymentRepository.count(), Matchers.is(1L));
		assertTrue(paymentRepository.findOne(Example.of(saved)).isPresent());		
	}
	
	@Test
	public void testSaveAndDeletePayment() {		
		//given
		Payment payment = this.newPayment(PaymentType.CREDIT_CARD);
		
		//when
		Payment saved = paymentRepository.save(payment);
		paymentRepository.delete(saved);
		
		//then
		assertThat(paymentRepository.count(), Matchers.is(0L));		
	}
	
	private Payment newPayment(PaymentType type) {
		
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
		List<Customer> customers = customerRepository.findAll();
		if (CollectionUtils.isEmpty(customers)) {
			throw new IllegalStateException("customer repository is empty! Listener was executed?");
		}
		customer = customers.stream().findFirst().get();
	}
}