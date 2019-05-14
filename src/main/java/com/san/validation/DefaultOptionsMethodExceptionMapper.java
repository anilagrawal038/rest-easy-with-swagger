package com.san.validation;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.DefaultOptionsMethodException;

@Provider
public class DefaultOptionsMethodExceptionMapper implements ExceptionMapper<DefaultOptionsMethodException> {

	@Override
	public Response toResponse(DefaultOptionsMethodException exception) {
		System.out.println("DefaultOptionsMethodExceptionMapper invoked");
		return Response.status(Status.OK) //
				.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS") //
				.header("Access-Control-Allow-Origin", "*") //
				.header("Access-Control-Allow-Headers", "*") //
				.build();
	}

}
