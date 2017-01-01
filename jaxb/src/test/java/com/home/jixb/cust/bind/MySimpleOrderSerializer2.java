package com.home.jixb.cust.bind;


import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.SchemaFactory;

public class MySimpleOrderSerializer2 {
  public static void main(String[] args) throws Exception {
    
    MySimpleOrder myOrder = new MySimpleOrder();
    myOrder.setBillTo(new MyAddress());
    myOrder.getBillTo().setName("John Doe");
    myOrder.getBillTo().setStreet("125 Main Street");
    myOrder.getBillTo().setCity("Any Town");
    myOrder.getBillTo().setState("NM");
    myOrder.getBillTo().setZip("95811");
    myOrder.getBillTo().setPhone("(831) 874-1123");
    Items items = new Items();
    myOrder.setItems(items);
    List<MyItem> itemList = items.getItem();
    MyItem myItem = new MyItem();
    myItem.setPrice((float) 2.99);
    myItem.setQuantity(6);
    myItem.setProductName("Diet Coke");
    itemList.add(myItem);
    myItem = new MyItem();
    myItem.setPrice((float) 3.99);
    myItem.setQuantity(4);
    myItem.setProductName("Potato Chips");
    itemList.add(myItem);
    myItem = new MyItem();
    myItem.setPrice((float) 5.34);
    myItem.setQuantity(2);
    myItem.setProductName("Frozen Pizza");
    itemList.add(myItem);
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(MySimpleOrder.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, 
          Boolean.TRUE);
      SchemaFactory sf = 
        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      ByteArrayOutputStream ba = new ByteArrayOutputStream();
      jaxbMarshaller.marshal(myOrder, ba);
      System.out.println(ba.toString());
     } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
