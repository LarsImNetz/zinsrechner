package de.hypoport.einarbeitung.lars;

import java.io.Serializable;

import org.apache.wicket.validation.IErrorMessageSource;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;

public class DoubleValidator implements IValidator<Double> {

		@Override
		public void validate(IValidatable<Double> validatable) {
			if (validatable == null) {return;}
			
			final Double value = validatable.getValue();
			if (value < 0) {
				validatable.error(new IValidationError() {

					@Override
					public Serializable getErrorMessage(
							IErrorMessageSource messageSource) {
						return "Die Miete sollte ein positiver Wert sein.";
					}
				});
			}
		}

}
