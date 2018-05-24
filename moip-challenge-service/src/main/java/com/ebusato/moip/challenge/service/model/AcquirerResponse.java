package com.ebusato.moip.challenge.service.model;

import com.ebusato.moip.challenge.persistence.model.payment.PaymentStatus;
import com.google.common.base.MoreObjects;

public class AcquirerResponse {
	
	private PaymentStatus status;
	private String transactionId;
	
	public AcquirerResponse(PaymentStatus status, String transactionId) {
		super();
		this.status = status;
		this.transactionId = transactionId;
	}
	
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)	
				.add("status",  status)
					.add("transactionId", transactionId)
						.toString();		
	}	
}