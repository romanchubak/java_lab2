
import Classes.Product;
import Classes.Product_Category;
import Classes.Warehouse;
import org.pojava.datetime.DateTime;
import IO.*;

/**
 * Created by romanchubak on 27.09.2016.
 */
public class main{
    public static void main(String... strings)
    {
        Product P1 = new Product( "Beer" ,  Product_Category.ALCOHOL);
        Product P2 = new Product( "Rom" ,Product_Category.ALCOHOL);


        Warehouse h1 = new Warehouse("Abka");
        Warehouse h2 = new Warehouse();
        h1.setProducts(P1,3);
        h1.setProducts(P2,2);


        IO io = new FileIO();
        String s = "test_file";
        io.WriteToFile(h1, s);
        h2 = io.ReadFromFile(s);
        System.out.println( h2.toString());
        System.out.println("Done!!!");
    }


}
