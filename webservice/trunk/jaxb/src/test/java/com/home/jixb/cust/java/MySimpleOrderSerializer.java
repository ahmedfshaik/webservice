package com.home.jixb.cust.java;

import java.io.ByteArrayOutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.SchemaFactory;

public class MySimpleOrderSerializer {

  public static void main(String[] args) throws Exception {
    
    MySimpleOrder myOrder = new MySimpleOrder(
        "John Doe",
        "125 Main Street",
        "Any Town", "NM", "95811",
        "(831) 874-1123");
    myOrder.getItemList().add(new MyItem(6, (float) 2.99, "Diet Coke"));
    myOrder.getItemList().add(new MyItem(4, (float) 3.99, "Potato Chips"));
    myOrder.getItemList().add(new MyItem(2, (float) 5.34, "Frozen Pizza"));
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(MySimpleOrder.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, 
          Boolean.TRUE);
      SchemaFactory sf = 
        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//      Schema schema = sf.newSchema(new File("samples/schema1.xsd"));
      //jaxbMarshaller.setSchema(schema);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      jaxbMarshaller.marshal(myOrder, baos);
      System.out.println(baos.toString());
      /*Unmarshaller u = jaxbContext.createUnmarshaller();
      MySimpleOrder roundTripOrder = 
        (MySimpleOrder) u.unmarshal(new StringReader(baos.toString()));
      System.out.println("phone = " + roundTripOrder.getBillTo().phone);*/
      
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
