package edu.home.webservice;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface OrderProcess {
	@WebMethod
	//returns unique order ID back to customer
	String processOrder(Order order);
}
