//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.11 at 10:36:17 PM IST 
//


package com.home.jixb.std;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.home.jixb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.home.jixb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SimpleOrder }
     * 
     */
    public SimpleOrder createSimpleOrder() {
        return new SimpleOrder();
    }

    /**
     * Create an instance of {@link SimpleOrder.BillTo }
     * 
     */
    public SimpleOrder.BillTo createSimpleOrderBillTo() {
        return new SimpleOrder.BillTo();
    }

    /**
     * Create an instance of {@link SimpleOrder.Items }
     * 
     */
    public SimpleOrder.Items createSimpleOrderItems() {
        return new SimpleOrder.Items();
    }

    /**
     * Create an instance of {@link ItemType }
     * 
     */
    public ItemType createItemType() {
        return new ItemType();
    }

}