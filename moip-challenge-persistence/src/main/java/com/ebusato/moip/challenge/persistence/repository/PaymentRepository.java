package com.ebusato.moip.challenge.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ebusato.moip.challenge.persistence.model.payment.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
