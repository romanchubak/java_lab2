package lab1;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 * Created by Роман on 27.09.2016.
 */
public class main{
    public static void main(String... strings)
    {
        Product P1 = new Product(Product_Category.ALCOHOL, "beer", new GregorianCalendar(1,1,1),new GregorianCalendar(1,1,1),new GregorianCalendar(1,1,1));
        Product P2 = new Product(Product_Category.MEAT, "beef", new GregorianCalendar(1,1,1),new GregorianCalendar(1,1,1),new GregorianCalendar(1,1,1));

        Warehouse h1 = new Warehouse();
        h1.setProducts(P1,3);
        h1.setProducts(P2,2);

        System.out.println( h1.Remove_Product(P1));
        System.out.println("Done!!!");
    }


}
