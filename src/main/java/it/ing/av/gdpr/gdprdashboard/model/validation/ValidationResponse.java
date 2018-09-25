package it.ing.av.gdpr.gdprdashboard.model.validation;

import java.util.List;

public class ValidationResponse {

	private ValidationStatus status;
	private List<ErrorMessage> errorMessageList;

	public ValidationStatus getStatus() {
		return status;
	}

	public void setStatus(ValidationStatus status) {
		this.status = status;
	}

	public List<ErrorMessage> getErrorMessageList() {
		return this.errorMessageList;
	}

	public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}

	public boolean isSuccess() {
		return status == ValidationStatus.SUCCESS;
	}

}