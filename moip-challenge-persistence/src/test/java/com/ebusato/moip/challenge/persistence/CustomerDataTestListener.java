package com.ebusato.moip.challenge.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.repository.CustomerRepository;

public class CustomerDataTestListener extends AbstractTestExecutionListener {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		testContext.getApplicationContext()
			.getAutowireCapableBeanFactory()
				.autowireBean(this);
		
		Customer customer = new Customer();
		customer.setName("John Doe");
		customer.setEmail("john@doe.com");
		customer.setCpf("00000000191");		
		customerRepository.save(customer);
		
	}
}
