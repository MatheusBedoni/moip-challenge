package com.ebusato.moip.challenge.api.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.ebusato.moip.challenge.api.controller.resource.PaymentDetailsResource;
import com.ebusato.moip.challenge.persistence.model.payment.PaymentType;

public class PaymentDetailsValidator implements ConstraintValidator<PaymentDetailsConstraint, PaymentDetailsResource> {

	@Override
	public boolean isValid(PaymentDetailsResource details, ConstraintValidatorContext context) {
		if (details == null || details.getType() == null) {
			return false;
		}
		if (details.getType() == PaymentType.CREDIT_CARD) {			
			if (details.getBrand() == null || details.getExpiration() == null) {
				return false;
			}
			if (StringUtils.isAnyBlank(details.getCvv(), details.getHolder(), details.getNumber())) {
				return false;
			}			
		}
		return true;
	}

}
