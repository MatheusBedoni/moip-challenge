package com.ebusato.moip.challenge.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ebusato.moip.challenge.persistence.model.Customer;
import com.ebusato.moip.challenge.persistence.repository.CustomerRepository;
import com.ebusato.moip.challenge.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	public Customer createIfAbsent(Customer customer) {
		Optional<Customer> customerFound = this.customerRepository.findOne(Example.of(customer));
		if (!customerFound.isPresent()) {
			Customer customerSaved = this.customerRepository.save(customer);
			customerFound = Optional.of(customerSaved);
		}
		return customerFound.get();
	}
}