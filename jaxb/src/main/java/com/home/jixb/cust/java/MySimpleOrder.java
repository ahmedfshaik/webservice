package com.home.jixb.cust.java;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="simpleOrder")
public class MySimpleOrder {

  private MyAddress billTo;
  private List<MyItem> itemList;

  // must add a no-arg constructor
  public MySimpleOrder() {}
    
  public MySimpleOrder(String name, String street, String city, String state,
      String zip, String phone) {
    this(new MyAddress(name, street, city, state, zip, phone));
  }
  
  public MySimpleOrder(MyAddress addr) {
    this.billTo = addr;
    itemList = new ArrayList<MyItem>();
  }

  public MyAddress getBillTo() {
    return billTo;
  }
  
  public void setBillTo(MyAddress billTo) {
    this.billTo = billTo;
  }

  @XmlElementWrapper(name="items")
  @XmlElement(name="item")
  public List<MyItem> getItemList() {
    return itemList;
  }
  
  public void setItemList(List<MyItem> itemList) {
    this.itemList = itemList;
  }
  
}


