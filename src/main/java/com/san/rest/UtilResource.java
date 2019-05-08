package com.san.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.san.to.ErrorTO;
import com.san.to.ResponseTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/status")
@Produces({ MediaType.APPLICATION_JSON })
@Api(value = "Application Status")
public class UtilResource {

	@GET
	@ApiOperation(value = "Fetch Application Status", response = ResponseTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, response = ErrorTO.class, message = "Invalid Request"), //
			@ApiResponse(code = 404, response = ErrorTO.class, message = "Resource Not Found"), //
			@ApiResponse(code = 500, response = ErrorTO.class, message = "Internal Server Error") //
	})
	public Response status() {
		ResponseTO response = new ResponseTO("Application is working fine");
		return Response.ok(response).build();
	}

}
