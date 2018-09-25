package it.ing.av.gdpr.gdprdashboard.controller.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import it.ing.av.gdpr.gdprdashboard.model.validation.ErrorMessage;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationStatus;
import it.ing.av.gdpr.gdprdashboard.model.validation.ValidationResponse;

public class ValidationResponseUtils {

	public static ValidationResponse validation(BindingResult result) {

		ValidationResponse validationResponse = new ValidationResponse();

		if (result.hasErrors()) {
			validationResponse.setStatus(ValidationStatus.FAIL);
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(),
						objectError.getField() + "  " + objectError.getDefaultMessage()));
			}
			validationResponse.setErrorMessageList(errorMesages);
		} else {
			validationResponse.setStatus(ValidationStatus.SUCCESS);
		}

		return validationResponse;

	}

}
