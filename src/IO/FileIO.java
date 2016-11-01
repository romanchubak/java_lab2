package IO;

/**
 * Created by romanchubak on 01.11.2016.
 */
import java.io.*;

import java.util.Scanner;

import java.util.regex.Matcher;

import java.util.regex.Pattern;
import org.pojava.datetime.DateTime;
import Classes.*;


    public class FileIO implements IO
{
    private static String WAREHOUSE_PATTERN = "(>Warehouse: )([A-Z][a-z]+)";

    private static String PRODUCT_PATTERN = "(>>>Product: )([A-Z][a-z]+)";

    private static String CATEGORY_PATTERN = "(>>>Category: )([A-Z]+)";

    private static String DATE_PATTERN = "(>>>\\w+: )(20[1-9][0-9]-[01][012]-[0123][0-9]\\s[012][0-9]:[0-5][0-9]:[0-6][0-9])";

    private static String PRODUCT_NUMBER_PATTERN = "(>>Product number: )(\\d+)";
    @Override
    public void WriteToFile(Warehouse w_house, String fileName )  {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter( new FileWriter(fileName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.print(w_house.toString());
        pw.close();
    }

    @Override
    public Warehouse  ReadFromFile(String fileName)
    {
        Scanner sc = null;
        FileReader fr = null;

        try {
            fr = new FileReader(fileName + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc = new Scanner(fr);
        Warehouse w_house = new Warehouse();

        String stLine = sc.nextLine();
        Pattern p = Pattern.compile(WAREHOUSE_PATTERN);
        Matcher m = p.matcher(stLine);
        if(m.find())
        {
            w_house.setName(stLine.substring(m.start(), m.end()));
        }

        while (sc.hasNextLine())
        {
            Product P = new Product();
            stLine = sc.nextLine();
            if(stLine.contains("-")) stLine = sc.nextLine();

            int prod_number = 0;
            p = Pattern.compile(PRODUCT_NUMBER_PATTERN);
            m = p.matcher(stLine);
            if(m.find()) prod_number = Integer.parseInt(m.group(2));

            stLine = sc.nextLine();
            p = Pattern.compile(PRODUCT_PATTERN);
            m = p.matcher(stLine);
            if(m.find()) P.setName(m.group(2));

            stLine = sc.nextLine();
            p = Pattern.compile(CATEGORY_PATTERN);
            m = p.matcher(stLine);
            if(m.find()) P.setCategory(Product_Category.valueOf(m.group(2)));

            stLine = sc.nextLine();
            p = Pattern.compile(DATE_PATTERN);
            m = p.matcher(stLine);
            if(m.find())  P.setDate_of_manufacture( new DateTime(m.group(2)));

            stLine = sc.nextLine();
            p = Pattern.compile(DATE_PATTERN);
            m = p.matcher(stLine);
            if(m.find())  P.setDate_of_admission( new DateTime(m.group(2)));

            stLine = sc.nextLine();
            p = Pattern.compile(DATE_PATTERN);
            m = p.matcher(stLine);
            if(m.find())  P.setDate_fitness( new DateTime(m.group(2)));

            w_house.setProducts(P,prod_number);
        }
        sc.close();
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return w_house;
    }



}
