package Classes;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.NotNull;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.ParserConfigurationException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

/**
 *  on 28.09.2016.
 */
@XmlRootElement(name="Warehouse")

@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse
{
    @XmlElement(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @XmlElement(name = "Products")
    @SerializedName("Products")
    @Expose
    private Map<Product, Integer> Products ;

    public Warehouse()
    {
        name = null;
        Products = null;
    }
    public Warehouse(String Name)
    {
        name = Name;
        Products = new HashMap<Product, Integer>();
    }


    public Map getProducts()
    {
        return Products;
    }
    public void setProducts(Product p, Integer n) {
        Products.put(p,n);
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String rez = ">Warehouse: " + name ;
        for(Map.Entry<Product, Integer> entry : Products.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();

            rez += "\n---------------------------------------------------------------\n" +
                    ">>Product number: " + value + "\n" + key.toString();
        }
        return  rez;
    }

    public LinkedList<Product> PrintProductsListByCategory(Product_Category category)
    {
        LinkedList<Product> productsList = new LinkedList<Product>();

        for(Map.Entry<Product, Integer> entry : Products.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();

            if( key.getCategory() == category)
                productsList.add(key);
        }

        return productsList;
    }
    public String PrintProductsListByCategory_String(Product_Category category)
    {
        String  productsList = "";

        for(Map.Entry<Product, Integer> entry : Products.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();

            if( key.getCategory() == category)
                productsList += key.getName();

        }

        return productsList;
    }
    public String Remove_Product(Product P)
    {
        String rez = "";
        if(Products.containsKey(P))
        {
            rez = "The " + P.getName() + " has been removed!";
            Products.remove(P);
        }
        else
            rez = "error!";
        return  rez;
    }

    public LinkedList<Product> PrintUnfitProducts()
    {
        LinkedList<Product> UnfitProducts = new LinkedList<Product>();
        for(Map.Entry<Product, Integer> entry : Products.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();

            if(  key.getDate_fitness().compareTo(key.getDate_of_manufacture()) < 0 )
                UnfitProducts.add(key);
        }
        return UnfitProducts;
    }

    public String PrintUnfitProducts_String()
    {
        String UnfitProducts = "";
        for(Map.Entry<Product, Integer> entry : Products.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();

            if(  key.getDate_fitness().compareTo(key.getDate_of_manufacture()) < 0 )
                UnfitProducts += key.getName();
        }
        return UnfitProducts;
    }

}
