package edu.home.rest.json;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {

    private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
    private AtomicInteger idCounter = new AtomicInteger();

    public CustomerResource() {
    }

    @POST
    @Consumes("application/json")
    public Response createCustomer(Customer customer) {
        customer.setId(idCounter.incrementAndGet());
        customerDB.put(customer.getId(), customer);
        System.out.println("Created customer " + customer.getId());
        return Response.created(URI.create("/customers/" + customer.getId())).build(); // Response
                                                                                       // without
                                                                                       // body
                                                                                       // having
                                                                                       // status
                                                                                       // code 201.
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getCustomer(@PathParam("id") int id) {
        Customer customer = customerDB.get(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(customer).build();// Response with body having status code 200
    }

    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        Customer customer = customerDB.get(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    } // The default status code is 204, NO CONTENT.
}
