package com.home.jaxb.std;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

import com.home.jixb.std.ItemType;
import com.home.jixb.std.SimpleOrder;
import com.home.jixb.std.SimpleOrder.BillTo;
import com.home.jixb.std.SimpleOrder.Items;

public class StandardSerializer {

	public static void main(String[] args) throws Exception {

		SimpleOrder order = new SimpleOrder();
		
		BillTo billTo = new BillTo();
		billTo.setName("John Doe");
		billTo.setStreet("125 Main Street");
		billTo.setCity("Any Town");
		billTo.setState("NM");
		billTo.setZip("95811");
		billTo.setPhone("(831) 874-1123");

	    ItemType it1 = new ItemType();
		it1.setProductName("Diet Coke");
		it1.setQuantity(new BigInteger("6"));
		it1.setPrice((float) 2.99);

		ItemType it2 = new ItemType();
		it2.setProductName("Potato Chips");
		it2.setQuantity(new BigInteger("4"));
		it2.setPrice((float) 3.99);

		Items items = new Items();
		List<ItemType> itemTypes = items.getItem();
		itemTypes.add(it1);
		itemTypes.add(it2);

		order.setBillTo(billTo);
		order.setItems(items);

		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(SimpleOrder.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			//The below two lines are required only if we want to validate xml message againg xsd using jaxp1.3
			//Schema schema = sf.newSchema(new File("simpleorder.xsd"));
			//jaxbMarshaller.setSchema(schema);
			ByteArrayOutputStream ba = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(order, ba);
			System.out.println(ba.toString());
			Unmarshaller u = jaxbContext.createUnmarshaller();
			SimpleOrder roundTripOrder = (SimpleOrder) u.unmarshal(new StringReader(ba.toString()));
			System.out.println("phone = " + roundTripOrder.getBillTo().getPhone());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
