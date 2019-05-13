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
import org.jboss.resteasy.spi.NotFoundException;

import com.san.to.ErrorTO;

@Provider
public class InvalidRequestExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		System.out.println("InvalidRequestExceptionMapper invoked");
		ErrorTO error = new ErrorTO("Invalid request");
		Response httpResponse = null;
		if (exception instanceof MethodConstraintViolationException) {
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
			httpResponse = Response.status(Status.BAD_REQUEST).entity(error).header(HttpHeaders.CONTENT_TYPE, "application/json").build();
		} else if (exception instanceof NotFoundException) {
			error.setErrorMessage("Requested resource not found");
			httpResponse = Response.status(Status.NOT_FOUND).entity(error).header(HttpHeaders.CONTENT_TYPE, "application/json").build();
		} else {
			error.setErrorMessage("Some error occurred. Please contact to server administrator. Error Message : " + exception.getMessage());
			httpResponse = Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).header(HttpHeaders.CONTENT_TYPE, "application/json").build();
		}
		return httpResponse;
	}

}
