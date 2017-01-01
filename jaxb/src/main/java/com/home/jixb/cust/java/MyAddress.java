package com.home.jixb.cust.java;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "street", "city", "state", "zip",
		"phone" })
public class MyAddress {
	protected String name;
	protected String street;
	protected String city;
	protected String state;
	protected String zip;
	protected String phone;

	// need a no-arg constructor
	public MyAddress() {
	};

	// ! </example>

	public MyAddress(String name, String street, String city, String state,
			String zip, String phone) {

		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;

	}

}
