package edu.home.rest.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//CREATE TABLE CUSTOMER(ID NUMBER(10)PRIMARY KEY, FIRSTNAME VARCHAR2(100), LASTNAME VARCHAR2(100), STREET VARCHAR2(100), CITY VARCHAR2(100), STATE VARCHAR2(100), ZIP VARCHAR2(100), COUNTRY VARCHAR2(100));
@XmlRootElement(name = "customer")
public class Customer{
   private int id;
   private String firstName;
   private String lastName;
   private String street;
   private String city;
   private String state;
   private String zip;
   private String country;

   @XmlAttribute
   public int getId(){
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   @XmlElement(name = "first-name")
   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   @XmlElement(name = "last-name")
   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   @XmlElement
   public String getStreet()
   {
      return street;
   }

   public void setStreet(String street)
   {
      this.street = street;
   }

   @XmlElement
   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   @XmlElement
   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   @XmlElement
   public String getZip()
   {
      return zip;
   }

   public void setZip(String zip)
   {
      this.zip = zip;
   }

   @XmlElement
   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }
}
