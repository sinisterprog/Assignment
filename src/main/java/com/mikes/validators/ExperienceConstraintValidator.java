package com.mikes.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mikes.model.Experience;

public class ExperienceConstraintValidator implements
		ConstraintValidator<ExperienceType, Experience> {

	@Override
	public boolean isValid(Experience expField, ConstraintValidatorContext cxt) {
		if (expField == null) {
			return false;
		}
		if ((expField.getDescription() == null)
				|| (expField.getDescription().isEmpty())) {
			return false;
		}
		return true;
	}

	@Override
	public void initialize(ExperienceType arg0) {
		
	}

}
