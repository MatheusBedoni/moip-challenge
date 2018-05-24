package com.ebusato.moip.challenge.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebusato.moip.challenge.persistence.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
