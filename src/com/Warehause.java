package com;


import Classes.Product;
import Classes.Warehouse;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Map;

/**
 * Created by romanchubak on 01.11.2016.
 */
@XmlRootElement(name = "Warehouse")
@XmlType(propOrder = {"name","Products"})
public class Warehause {

    private String name;
    private Map<Product, Integer> Products ;

    public Warehause()
    {
        name = "";
        Products = null;
    }

    public Warehause(Classes.Warehouse W)
    {
        name = W.getName();
        Products = W.getProducts();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Map<Product, Integer> getProducts() {
        return Products;
    }
    public void setProducts(Map<Product, Integer> products) {
        Products = products;
    }

    @Override
    public String toString() {
        return "Warehause [ " +
                "name=" + name +
                ", Products=" + Products +
                "]";
    }
}
