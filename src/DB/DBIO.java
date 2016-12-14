package DB;

import Entity.Product;
import Entity.Product_Category;
import Entity.Warehouse;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.sun.deploy.net.proxy.ProxyUnavailableException;
import org.pojava.datetime.DateTime;
import Exception.DateException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by romanchubak on 30.11.2016.
 */
public class DBIO {

    public static void main(String ... args)
    {


        Product P = new Product( "Beer" ,  Product_Category.ALCOHOL, 4);
        Product P2 = new Product( "Rom" ,Product_Category.ALCOHOL, 5);
        Warehouse W = new Warehouse("Abka");

        W.addProduct(P);
        W.addProduct(P);

        Warehouse W2 = new Warehouse("fasdf");
        Product Pr1 = new Product("Bread",Product_Category.BAKERY,10);
        W2.addProduct(Pr1);


        DBIO db = new DBIO();
        //db.addProduct(P2,W);
        //db.addProduct(P,W);
       //db.updateProduct(new Product("Beer",Product_Category.BAKERY, 5),W);
        //db.deleteProduct(P,W);
        //db.deleteAllProductByIdWarehouse(W);
        //db.getRoutesByDepot(W);
        //System.out.print(db.getProductByIdWarehouse(W).toString());
        //db.addWarehouse(W);
        //db.addWarehouse(W2);
        //db.updateWarehouse(W);
        //for (Warehouse warehouse : db.getAllWarehouse()) {
        //System.out.print(warehouse.toString());
        //}
        db.deleteWarehouse(W2);
    }

    public  void addProduct(Product product , Warehouse warehouse) {
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
    public void updateProduct(Product product, Warehouse warehouse) {
        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(

                    "UPDATE  product SET Category=?, Quantity=?, Date_of_manufacture=?, Dateof_admission=?, Date_fitness=?  WHERE Name=? AND Warehouse_id=?;"
            );
            st.setString(1, product.getCategory().toString());
            st.setInt(2, product.getCount());
            st.setDate(3,new java.sql.Date(product.getDate_of_manufacture().toMillis()));
            st.setDate(4, new java.sql.Date(product.getDate_of_admission().toMillis()));
            st.setDate(5, new java.sql.Date(product.getDate_fitness().toMillis()));
            st.setString(6, product.getName() );
            st.setInt(7, warehouse.getId());

            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteProduct(Product product, Warehouse warehouse) {
        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(

                    "DELETE FROM product WHERE Name=? AND Warehouse_id=?;"
            );
            st.setInt(2,warehouse.getId());
            st.setString(1,product.getName());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if ( connection != null) {
                ConnectionManager.close();
            }
        }
    }
    public void deleteAllProductByIdWarehouse(Warehouse warehouse) {
        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM product WHERE Warehouse_id=?;"
            );
            st.setInt(1,warehouse.getId());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if ( connection != null) {
                ConnectionManager.close();
            }
        }
    }
    public List<Product> getProductByIdWarehouse(Integer IdWarehouse) {

        Connection connection = ConnectionManager.getConnection();
        List<Product> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM product WHERE Warehouse_id=" + IdWarehouse +";";
            ResultSet rs = statement.executeQuery(query);
            Product product = null;
            while (rs.next()) {
                //Name, Category, Quantity, Date_of_manufacture, Dateof_admission, Date_fitness, Warehouse_id
                String Name = rs.getString("Name");
                String Category = rs.getString("Category");
                Integer Quantity = rs.getInt("Quantity");
                DateTime Date_of_manufacture = new DateTime(rs.getDate("Date_of_manufacture").toString());
                DateTime Dateof_admission = new DateTime(rs.getDate("Dateof_admission").toString());
                DateTime Date_fitness = new DateTime(rs.getDate("Date_fitness").toString());


                product = new Product(Product_Category.valueOf(Category),Name,Date_of_manufacture,Dateof_admission,Date_fitness,Quantity);

                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DateException e) {
            e.printStackTrace();
        }
        return list;
    }


    public  void addWarehouse(Warehouse warehouse){
        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(
                    "INSERT INTO warehouse " +
                            "(idWarehouse, Name) VALUES \n" +
                            "(?, ?);"
            );
            st.setInt(1, warehouse.getId());
            st.setString(2, warehouse.getName());
            st.execute();
            for ( Product product : warehouse.getProducts()) {
                addProduct(product,warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if ( connection != null) {
                ConnectionManager.close();
            }
        }
    }
    public void updateWarehouse(Warehouse warehouse) {

        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(
                    "UPDATE warehouse SET Name=? WHERE idWarehouse=?;"
            );
            st.setString(1, warehouse.getName());
            st.setInt(2, warehouse.getId());
            st.execute();
            for ( Product product : warehouse.getProducts()) {
                updateProduct(product, warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if ( connection != null) {
                ConnectionManager.close();
            }
        }
    }
    public List<Warehouse> getAllWarehouse() {
        Connection connection = ConnectionManager.getConnection();
        List<Warehouse> list = new ArrayList<Warehouse>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM warehouse");
            while (rs.next()) {
                list.add(new Warehouse(rs.getString("Name"),getProductByIdWarehouse(rs.getInt("idWarehouse")),rs.getInt("idWarehouse")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void deleteWarehouse(Warehouse warehouse) {

        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement st = connection.prepareStatement(
                    "DELETE FROM warehouse WHERE idWarehouse=?;"
            );
            st.setInt(1,warehouse.getId());
            st.execute();
            deleteAllProductByIdWarehouse(warehouse);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if ( connection != null) {
                ConnectionManager.close();
            }
        }
    }



}
