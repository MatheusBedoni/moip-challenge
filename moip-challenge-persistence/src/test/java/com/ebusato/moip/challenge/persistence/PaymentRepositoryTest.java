package com.ebusato.moip.challenge.persistence;

import static com.ebusato.moip.challenge.persistence.MoipPersistenceTestUtils.newPayment;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;
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
		Payment payment = newPayment(PaymentType.CREDIT_CARD, customer);
		
		//when
		Payment saved = paymentRepository.save(payment);

		//then
		assertThat(saved.getType(), IsEqual.equalTo(PaymentType.CREDIT_CARD));
		assertThat(saved.getId(), Matchers.is(Matchers.notNullValue()));
	}
	
	@Test
	public void testSaveBoletoPayment() {		
		//given
		Payment payment = newPayment(PaymentType.BOLETO, customer);
		
		//when
		Payment saved = paymentRepository.save(payment);
		
		//then
		assertThat(saved.getType(), IsEqual.equalTo(PaymentType.BOLETO));
		assertThat(saved.getId(), Matchers.is(Matchers.notNullValue()));
	}
	
	@Test
	public void testSaveAndRetrievePayment() {		
		//given
		Payment payment = newPayment(PaymentType.CREDIT_CARD, customer);
		
		//when
		Payment saved = paymentRepository.save(payment);
		
		//then
		assertThat(paymentRepository.count(), Matchers.is(1L));
		assertTrue(paymentRepository.findOne(Example.of(saved)).isPresent());		
	}
	
	@Test
	public void testSaveAndDeletePayment() {		
		//given
		Payment payment = newPayment(PaymentType.CREDIT_CARD, customer);
		
		//when
		Payment saved = paymentRepository.save(payment);
		paymentRepository.delete(saved);
		
		//then
		assertThat(paymentRepository.count(), Matchers.is(0L));		
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