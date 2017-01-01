package edu.home.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.home.webservice.Order;
import edu.home.webservice.OrderProcess;

public class ClientOrder {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
		
		OrderProcess client = (OrderProcess)context.getBean("orderClient");
		
		// Populate the Order bean
		Order order = new Order();
		order.setCustomerID("C001");
		order.setItemID("I001");
		order.setQty(100);
		order.setPrice(200.00);
		String orderID = client.processOrder(order);
		System.out.println("Order ID:"+orderID);
	}
}
