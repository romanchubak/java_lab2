package ParseAndValidator;

import Classes.Product;
import Classes.Product_Category;
import Exception.DateException;
import com.sun.istack.internal.NotNull;
import org.pojava.datetime.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by romanchubak on 31.10.2016.
 */
//http://www.quizful.net/post/Java-RegExpйй
public class ProductValidator {
    private static String FULL_PATTERN = "^[A-Z]+\\s[A-Z][a-z]+\\s"+
                                         "20[1-9][0-9]-[01][012]-[0123][0-9]\\s[012][0-9]:[0-5][0-9]:[0-6][0-9]\\s"+
                                         "20[1-9][0-9]-[01][012]-[0123][0-9]\\s[012][0-9]:[0-5][0-9]:[0-6][0-9]\\s"+
                                         "20[1-9][0-9]-[01][012]-[0123][0-9]\\s[012][0-9]:[0-5][0-9]:[0-6][0-9]$";

    private static String WITHOUT_DATE_PATTERN = "[A-Z]+\\s[A-Z][a-z]+";//\\,\\s";

    private static String DATE_PATTERN = "20[1-9][0-9]-[01][012]-[0123][0-9]\\s[012][0-9]:[0-5][0-9]:[0-6][0-9]";

    private static String CATEGORY_PATTERN = "[A-Z]+\\s";

    private static String NAME_PATTERN = "[A-Z][a-z]+\\s";

    public static Product Parse(@NotNull String str) throws DateException
    {
        Pattern p = Pattern.compile(FULL_PATTERN);
        Matcher m = p.matcher(str);
        if(m.find())
        {
            return  ParseFullProduct(str);
        }

        p = Pattern.compile(WITHOUT_DATE_PATTERN);
        m = p.matcher(str);
        if(m.find())
        {
            return  ParseProductWithoutDate(str);
        }

        return new Product();
    }

    private static Product ParseProductWithoutDate(@NotNull String str) throws DateException
    {
        String name = "";
        Pattern p = Pattern.compile(NAME_PATTERN);
        Matcher m = p.matcher(str);
        if(m.find()) name = str.substring(m.start(), m.end()-1);

        Product_Category category = null;
        p = Pattern.compile(CATEGORY_PATTERN);
        m = p.matcher(str);
        if(m.find()) category = Product_Category.valueOf(str.substring(m.start(), m.end()-1));

        return new Product(name,category);
    }

    private static Product ParseFullProduct(@NotNull String str) throws DateException
    {
        String name = "";
        Pattern p = Pattern.compile(NAME_PATTERN);
        Matcher m = p.matcher(str);
        if(m.find()) name = str.substring(m.start(), m.end()-1);

        Product_Category category = null;
        p = Pattern.compile(CATEGORY_PATTERN);
        m = p.matcher(str);
        if(m.find()) category = Product_Category.valueOf(str.substring(m.start(), m.end()-1));

        DateTime[] date = new DateTime[3];
        p = Pattern.compile(DATE_PATTERN);
        m = p.matcher(str);
        int i = 0;
        while (m.find()) date[i++] = new DateTime(m.group());
        if(!DateValidator(date[0],date[2])) throw new DateException();

        return new Product(category,name,date[0],date[1],date[2]);
    }

    private static boolean DateValidator(@NotNull DateTime dateTime1,@NotNull DateTime dateTime2)
    {
        return dateTime1.compareTo(dateTime2) < 0;
    }


    public static void main(String... strings)
    {

        String s = "ALCOHOL Beer 2016-10-21 13:00:00 2016-10-29 13:00:00 2016-10-01 12:00:00";

        Product p;

        try
        {
            p = Parse(s);
        }
        catch (DateException d)
        {
            System.out.println(d.toString());
            return;
        }

        System.out.println(p.toString());

    }
}
