package com.ebusato.moip.challenge.service;

import com.ebusato.moip.challenge.persistence.model.Customer;

/**
 * Customer service layer.
 * @author Elias
 */
public interface CustomerService {

	/**
	 * Creates given customer if not exists, otherwise returns found customer.
	 * @param customer customer to create or retrieve
	 * @return customer
	 */
	Customer createIfAbsent(Customer customer);
}
