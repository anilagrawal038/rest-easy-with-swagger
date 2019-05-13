package com.san.validation;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.NotFoundException;

import com.san.to.ErrorTO;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		System.out.println("ResourceNotFoundExceptionMapper invoked");
		ErrorTO error = new ErrorTO("Invalid request");
		error.setErrorMessage("Requested resource not found");
		return Response.status(Status.NOT_FOUND).entity(error).header(HttpHeaders.CONTENT_TYPE, "application/json").build();
	}

}
