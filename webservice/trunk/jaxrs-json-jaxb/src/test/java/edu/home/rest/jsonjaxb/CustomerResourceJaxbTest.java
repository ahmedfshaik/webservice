package edu.home.rest.jsonjaxb;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.SchemaFactory;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerResourceJaxbTest {

    @Test
    public void testInsertCustomer() throws Exception {
        System.out.println("*** Create a new Customer ***");
        // Create a new customer
        /*
         * String newCustomer = "<customer>" + "<first-name>Bill</first-name>" +
         * "<last-name>Burke</last-name>" + "<street>256 Clarendon Street</street>" +
         * "<city>Boston</city>" + "<state>MA</state>" + "<zip>02115</zip>" +
         * "<country>USA</country>" + "</customer>";
         */

        Customer cust = new Customer();
        cust.setFirstName("Bill");
        cust.setLastName("Burke");
        cust.setStreet("256 Clarendon Street");
        cust.setCity("Boston");
        cust.setState("MA");
        cust.setZip("02115");
        cust.setCountry("US");

        String newCustomer = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // SchemaFactory sf = SchemaFactory
            // .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // The below two lines are required only if we want to validate xml message againg xsd
            // using jaxp1.3
            // Schema schema = sf.newSchema(new File("simpleorder.xsd"));
            // jaxbMarshaller.setSchema(schema);
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            jaxbMarshaller.marshal(cust, ba);
            newCustomer = ba.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        URL postUrl = new URL("http://localhost:9090/JAXRS-JSON-JAXB/customers");
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
    public void testReadCustomer() throws Exception {
        // Get the new customer
        System.out.println("*** GET Created Customer **");
        URL getUrl = new URL("http://localhost:9090/JAXRS-JSON-JAXB/customers/1");
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.setRequestMethod("GET");
        System.out.println("Content-Type: " + connection.getContentType());

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
        }
        Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
        connection.disconnect();
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        // Update the new customer. Change Bill's name to William
        String updateCustomer = "<customer>" + "<first-name>William</first-name>" + "<last-name>Burke</last-name>"
                + "<street>256 Clarendon Street</street>" + "<city>Boston</city>" + "<state>MA</state>" + "<zip>02115</zip>"
                + "<country>USA</country>" + "</customer>";
        URL getUrl = new URL("http://localhost:9090/JAXRS-JSON-JAXB/customers/1");
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/xml");
        OutputStream os = connection.getOutputStream();
        os.write(updateCustomer.getBytes());
        os.flush();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        System.out.println("**** After Update ***");
        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
        }
        connection.disconnect();
    }
}
