package com.san.to;

public class ErrorTO {

	public ErrorTO(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
