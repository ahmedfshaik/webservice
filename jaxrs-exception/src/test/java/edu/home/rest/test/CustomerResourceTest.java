package edu.home.rest.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerResourceTest{

   @Test
   public void testInsertCustomer() throws Exception{
      System.out.println("*** Create a new Customer ***");
      // Create a new customer
      String newCustomer = "<customer>"
              + "<first-name>Bill</first-name>"
              + "<last-name>Burke</last-name>"
              + "<street>256 Clarendon Street</street>"
              + "<city>Boston</city>"
              + "<state>MA</state>"
              + "<zip>02115</zip>"
              + "<country>USA</country>"
              + "</customer>";

      URL postUrl = new URL("http://localhost:9090/JAXRS-EXCEPTION/customers");
      HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
      connection.setDoOutput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/xml");
      OutputStream os = connection.getOutputStream();
      os.write(newCustomer.getBytes());
      os.flush();
      Assert.assertEquals(HttpURLConnection.HTTP_CREATED, connection.getResponseCode());
      System.out.println("Location: " + connection.getHeaderField("Location"));
      connection.disconnect();
   }

   @Test
   public void testReadCustomer() throws Exception{
      // Get the new customer
      System.out.println("*** GET Created Customer **");
      URL getUrl = new URL("http://localhost:9090/JAXRS-EXCEPTION/customers/101");
      HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
      connection.setRequestMethod("GET");
      
	  System.out.println("Content-Type: " + connection.getContentType());
      
	  BufferedReader reader = new BufferedReader(new
              InputStreamReader(connection.getErrorStream()));
      String line = reader.readLine();
	  while (line != null){
         System.out.println(line);
         line = reader.readLine();
      }
      Assert.assertEquals(HttpURLConnection.HTTP_NOT_FOUND, connection.getResponseCode());
      connection.disconnect();
   }
}
