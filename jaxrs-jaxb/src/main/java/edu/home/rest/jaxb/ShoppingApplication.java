package edu.home.rest.jaxb;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ShoppingApplication extends Application {
	private Set<Object> s = new HashSet<Object>();
	private Set<Class<?>> e = new HashSet<Class<?>>();

	public ShoppingApplication() {
		s.add(new CustomerResource());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return e;
	}

	@Override
	public Set<Object> getSingletons() {
		return s;
	}
}
