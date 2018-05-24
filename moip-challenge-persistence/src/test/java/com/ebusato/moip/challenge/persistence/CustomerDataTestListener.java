package com.ebusato.moip.challenge.persistence;

import static com.ebusato.moip.challenge.persistence.MoipPersistenceTestUtils.newCustomer;

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
		
		Customer customer = newCustomer();		
		customerRepository.save(customer);		
	}
}
