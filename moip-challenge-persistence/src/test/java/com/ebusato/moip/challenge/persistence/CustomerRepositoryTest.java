package com.ebusato.moip.challenge.persistence;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=MoipChallengePersistenceConfig.class)
@DataMongoTest
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;
			
	@Before 
	public void setUp() {
		customerRepository.deleteAll();
	}
	
	@Test
	public void testSaveCustomer() {		
		//given
		Customer customer = this.newCustomer();
		
		//when
		Customer saved = customerRepository.save(customer);

		//then
		assertThat(saved.getId(), Matchers.is(Matchers.notNullValue()));
	}
	
	
	@Test
	public void testSaveAndRetrieveCustomer() {		
		//given
		Customer customer = this.newCustomer();
				
		//when
		Customer saved = customerRepository.save(customer);

		//then		
		assertThat(customerRepository.count(), Matchers.is(1L));
		assertTrue(customerRepository.findOne(Example.of(saved)).isPresent());		
	}
	
	@Test
	public void testSaveAndDeletePayment() {		
		//given
		Customer customer = this.newCustomer();
						
		//when
		Customer saved = customerRepository.save(customer);
		customerRepository.delete(saved);

		//then
		assertThat(customerRepository.count(), Matchers.is(0L));		
	}
	
	private Customer newCustomer() {		
		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setEmail("john@doe.com");
		customer.setCpf("00000000191");		
		return customer;
	}
}