package com.san.validation;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;

import com.san.to.ErrorTO;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<MethodConstraintViolationException> {

	@Override
	public Response toResponse(MethodConstraintViolationException exception) {
		System.out.println("ValidationExceptionMapper invoked");
		ErrorTO error = new ErrorTO("Invalid request");
		MethodConstraintViolationException exp = (MethodConstraintViolationException) exception;
		Set<MethodConstraintViolation<?>> contsraints = exp.getConstraintViolations();
		Set<String> errors = new HashSet<String>();
		for (MethodConstraintViolation<?> contsraint : contsraints) {
			errors.add(contsraint.getMessage());
		}
		if (errors.size() > 0) {
			if (errors.size() == 1) {
				error.setErrorMessage(errors.iterator().next().toString());
			} else {
				error.setErrorMessage(errors.toString());
			}
		} else {
			error.setErrorMessage("Invalid arguments provided");
		}
		return Response.status(Status.BAD_REQUEST).entity(error).header(HttpHeaders.CONTENT_TYPE, "application/json").build();
	}

}
