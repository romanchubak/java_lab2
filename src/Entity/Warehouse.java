package Entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.*;

/**
 * on 28.09.2016.
 */

@XmlRootElement(name="Warehouse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Warehouse {

    @XmlElement(name = "name")
    @SerializedName("name")
    private String name;

    @XmlElement(name = "products")
    @SerializedName("products")
    private Collection<Product> products;

    public Warehouse() {
        name = null;
        products = new LinkedList<>();
    }

    public Warehouse(String Name) {
        name = Name;
        products = new LinkedList<>();
    }


    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String rez = name;
        for (Product product : products) {
            rez += product.toString() + '\n';
        }

        return rez;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> PrintProductsListByCategory(Product_Category category) {
        List<Product> productsList = new LinkedList<Product>();

        for (Product product : products) {
            if (product.getCategory() == category) {
                productsList.add(product);
            }
        }
        return productsList;
    }

    public String PrintProductsListByCategory_String(Product_Category category) {
        String productsList = "";

        for (Product product : products) {
            if (product.getCategory() == category) {
                productsList += product.toString() + "/n" ;
            }
        }
        return productsList;
    }

    public String Remove_Product(Product P) {
        String rez = "";
        if (products.remove(P)) {
            rez = "The " + P.getName() + " has been removed!";
        } else {
            rez = "error!";
        }
        return rez;
    }

    public List<Product> PrintUnfitProducts() {
        List<Product> UnfitProducts = new LinkedList<Product>();
        for (Product prod : products) {
            if (prod.getDate_fitness().compareTo(prod.getDate_of_manufacture()) < 0)
                UnfitProducts.add(prod);
        }
        return UnfitProducts;
    }

    public String PrintUnfitProducts_String() {
        String UnfitProducts = "";
        for (Product prod : products) {
            if (prod.getDate_fitness().compareTo(prod.getDate_of_manufacture()) < 0)
                UnfitProducts += prod.getName();
        }
        return UnfitProducts;
    }
}
