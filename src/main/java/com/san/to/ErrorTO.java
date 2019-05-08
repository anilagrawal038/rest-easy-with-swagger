package com.san.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Error", description = "Error Response Model")
public class ErrorTO {

	public ErrorTO(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@ApiModelProperty(value = "error description")
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
