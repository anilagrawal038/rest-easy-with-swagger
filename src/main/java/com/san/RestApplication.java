package com.san;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.san.rest.StudentResource;
import com.san.rest.UtilResource;

public class RestApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	// Issue with preflight requests in RESTEasy
	// - https://stackoverflow.com/questions/8077335/options-request-for-restful-cross-domain-using-cors

	public RestApplication() {
		singletons.add(new UtilResource());
		singletons.add(new StudentResource());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
		resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		resources.add(com.san.validation.ResourceNotFoundExceptionMapper.class);
		resources.add(com.san.validation.ValidationExceptionMapper.class);
		resources.add(com.san.validation.DefaultOptionsMethodExceptionMapper.class);
		resources.add(com.san.validation.InvalidRequestExceptionMapper.class);
		return resources;
	}

}