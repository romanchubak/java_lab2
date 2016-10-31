package Classes;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

/**
 *  on 28.09.2016.
 */
public class Warehouse
{
    private Map<Product, Integer> Products = new HashMap<Product, Integer>();

    public Map getProducts()
    {
        return Products;
    }
    public void setProducts(Product p, Integer n) {
        Products.put(p,n);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "Products=" + Products +
                '}';
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

}
