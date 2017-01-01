package edu.home.rest.json;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import java.net.URL;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerResourceTest{
   @Test
   public void testInsertCustomer() throws Exception{
      System.out.println("*** Create a new Customer ***");
      // Create a new customer
      String newCustomer = "{\"state\":\"MA\",\"country\":\"USA\",\"firstName\":\"Bill\",  \"lastName\":\"Burke\",\"street\":\"256 Clarendon Street\",\"zip\":\"02115\",\"city\":\"Boston\"}";

      URL postUrl = new URL("http://localhost:9090/JAXRS/customers");
      HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
      connection.setDoOutput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");
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
      URL getUrl = new URL("http://localhost:9090/JAXRS/customers/1");
      HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
      connection.setRequestMethod("GET");
      System.out.println("Content-Type: " + connection.getContentType());

      BufferedReader reader = new BufferedReader(new
              InputStreamReader(connection.getInputStream()));

      String line = reader.readLine();
      while (line != null){
         System.out.println(line);
         line = reader.readLine();
      }
      Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
      connection.disconnect();
	}

	/*@Test
	public void testDeleteRead() throws Exception{
      System.out.println("*** DELETE existing customer **");
      URL getUrl = new URL("http://localhost:9090/JAXRSJSON/customers/1");
      HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
      connection.setRequestMethod("DELETE");
      Assert.assertEquals(HttpURLConnection.HTTP_NO_CONTENT, connection.getResponseCode());
	  //Assert.assertEquals(HttpURLConnection.HTTP_NOT_FOUND, connection.getResponseCode());
      connection.disconnect();
   }*/
}
