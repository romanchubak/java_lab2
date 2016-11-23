
import Entity.Product;
import Entity.Product_Category;
import Entity.Warehouse;
import IO.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by romanchubak on 27.09.2016.
 */
public class main{
    public static void main(String... strings) {
        Product P1 = new Product( "Beer" ,  Product_Category.ALCOHOL, 4);
        Product P2 = new Product( "Rom" ,Product_Category.ALCOHOL, 4);

        Warehouse h1 = new Warehouse("Abka");
        Warehouse h2 = new Warehouse();
        h1.addProduct(P1);
        h1.addProduct(P2);


        IO io = new FileIO();
        IO io1 = new JsonIO();
        IO io2 = new XmlIO();
        String s = "output.xml";
        //io.WriteToFile(h1, s);
        /*try {
            io2.WriteToFile(h1, s);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            Warehouse warehouse = io2.readFromFile(s);
            System.out.println("Done!!!" + warehouse.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println( h2.toString());
    }


}
