package edu.home.rest.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import edu.home.rest.mapper.NotFoundExceptionMapper;

public class ShoppingApplication
        extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public ShoppingApplication() {
        singletons.add(new CustomerResource());
        singletons.add(new NotFoundExceptionMapper());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
