package com.san.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Response", description = "Generic Response Model")
public class ResponseTO {

	public ResponseTO(String message) {
		this.message = message;
	}

	@ApiModelProperty(value = "response message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
