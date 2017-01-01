package edu.home.rest.jaxb;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource{
   private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
   private AtomicInteger idCounter = new AtomicInteger();

   public CustomerResource(){ }

   @POST
   @Consumes("application/xml")
   @Produces("application/xml")
   public Response createCustomer(Customer customer){
      customer.setId(idCounter.incrementAndGet());
      customerDB.put(customer.getId(), customer);
      System.out.println("Created customer " + customer.getId());
	  //return customer; //Response with body having default status code 200
      //return Response.created(URI.create("/customers/" + customer.getId())).build(); //Response without body having status code 201
	  return Response.created(URI.create("/customers/" + customer.getId())).entity(customer).build(); //Response with body having status code 201
   }

   @GET
   @Path("{id}")
   @Produces("application/xml")
   public Response getCustomer(@PathParam("id") int id){
      Customer customer = customerDB.get(id);
	  if (customer == null)
      {
         throw new WebApplicationException(Response.Status.NOT_FOUND);
      }
		//return customer; //Response with body having default status code 200
	  return Response.ok(customer).build(); //Response with body having status code 200
   }

   @PUT
   @Path("{id}")
   @Consumes("application/xml")
   @Produces("application/xml")
   public Response updateCustomer(@PathParam("id") int id, Customer update){
      Customer customer = customerDB.get(id);
      if (customer == null) throw new WebApplicationException(Response.Status.NOT_FOUND);

      customer.setFirstName(update.getFirstName());
      customer.setLastName(update.getLastName());
      customer.setStreet(update.getStreet());
      customer.setState(update.getState());
      customer.setZip(update.getZip());
      customer.setCountry(update.getCountry());
	  //return customer; //Response with body having default status code 200
	  //return Response.noContent().build(); //Response without body with status code 204
		return Response.ok(customer).build(); //Response with body having status code 200
   }
}
