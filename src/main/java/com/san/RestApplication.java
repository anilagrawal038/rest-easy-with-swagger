package com.san;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.san.rest.StudentService;

public class RestApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public RestApplication() {
		singletons.add(new StudentService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
