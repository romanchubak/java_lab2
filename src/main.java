
import Classes.Product;
import Classes.Product_Category;
import Classes.Warehouse;
import org.pojava.datetime.DateTime;

/**
 * Created by romanchubak on 27.09.2016.
 */
public class main{
    public static void main(String... strings)
    {
        Product P1 = new Product( "Beer" ,  Product_Category.ALCOHOL);
        Product P2 = new Product( "Rom" ,Product_Category.ALCOHOL);


        Warehouse h1 = new Warehouse();
        h1.setProducts(P1,3);
        h1.setProducts(P2,2);

        System.out.println( h1.toString());
        System.out.println("Done!!!");
    }


}
