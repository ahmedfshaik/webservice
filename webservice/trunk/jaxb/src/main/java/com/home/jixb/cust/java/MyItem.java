package com.home.jixb.cust.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "ItemType", propOrder = { "quantity", "price" })
public class MyItem {

	private int quantity;
	private float price;
	private String productName;

	// need a no-arg constructor
	public MyItem() {
	};

	public MyItem(int quantity, float price, String productName)
			throws Exception {
		if (productName == null) {
			throw new Exception("productName cannot be null");
		}
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@XmlAttribute
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
// ! </example>
