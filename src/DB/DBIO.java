package DB;

import Entity.Product;
import Entity.Product_Category;
import Entity.Warehouse;
import org.pojava.datetime.DateTime;
import Exception.DateException;
import java.sql.*;
import java.sql.Statement;


/**
 * Created by romanchubak on 30.11.2016.
 */
public class DBIO {

    public static void main(String ... args)
    {


        Product P = new Product( "Beer" ,  Product_Category.ALCOHOL, 4);
        Warehouse W = new Warehouse("Abka");
        W.addProduct(P);

        DBIO db = new DBIO();

        db.AddProduct(P,W);
    }

    public  void AddProduct(Product product , Warehouse warehouse)
    {
        Connection connection = ConnectionManager.getConnection();
        try {

            PreparedStatement st = connection.prepareStatement(

                    "INSERT INTO product " +

                            "( Name, Category, Quantity, Date_of_manufacture, Dateof_admission, Date_fitness, Warehouse_id) VALUES \n" +

                            "( ?, ?, ?, ?, ?, ?, ? );"

            );
            st.setString(1, product.getName());
            st.setString(2, product.getCategory().toString());
            st.setInt(3,product.getCount());
            st.setDate(4,new java.sql.Date(product.getDate_of_manufacture().toMillis()));
            st.setDate(5, new java.sql.Date(product.getDate_of_admission().toMillis()));
            st.setDate(6, new java.sql.Date(product.getDate_fitness().toMillis()));
            st.setInt(7, warehouse.getId());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
