package com.ebusato.moip.challenge.service;

import java.util.Optional;

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
import com.ebusato.moip.challenge.persistence.repository.CustomerRepository;

import static com.ebusato.moip.challenge.service.MoipServiceTestUtils.newCustomer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Before
	public void init() {
		Mockito.when(customerRepository.findOne(Mockito.any())).thenReturn(Optional.empty());
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(newCustomer());		
	}
	
	@Test
	public void testCreateOrRetrieveCustomer() {		
		//given
		Customer customer = newCustomer();
		
		//when
		Customer retrieved = customerService.createIfAbsent(customer);
		
		//then
		MatcherAssert.assertThat(retrieved, Matchers.equalTo(customer));
	}
}