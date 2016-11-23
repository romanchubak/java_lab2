package IO;

/**
 * Created by romanchubak on 01.11.2016.
 */

import java.io.*;

import java.util.Scanner;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.pojava.datetime.DateTime;
import Entity.*;


public class FileIO implements IO {
    private static String WAREHOUSE_PATTERN = "([A-Z][a-z]+)";

    private static String PRODUCT_PATTERN = "([A-Z][a-z]+)";

    private static String CATEGORY_PATTERN = "([A-Z]+)";

    // private static String DATE_PATTERN = "(>>>\\w+: )(20[1-9][0-9]-[01][012]-[0123][0-9]\\s[012][0-9]:[0-5][0-9]:[0-6][0-9])";

    private static String DATE_PATTERN = "(20[1-9][0-9]-[01][012]-[0123][0-9]\\s[012][0-9]:[0-5][0-9]:[0-6][0-9])";

    private static String PRODUCT_NUMBER_PATTERN = "(\\d+)";

    @Override
    public void WriteToFile(Warehouse warehouse,
                            String fileName)
            throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(fileName));
        pw.print(warehouse.toString());
        pw.close();
    }

    @Override
    public Warehouse readFromFile(String fileName)
            throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        Scanner scanner = new Scanner(fileReader);
        Warehouse warehouse = new Warehouse();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] split = line.split(",");

            String name = split[0];
            String count = split[1];
            String category = split[2];
            String date_of_manufacture = split[3];
            String date_of_admission = split[4];
            String date_fitness = split[5];

            try {
                Product product =
                        new Product(Product_Category.valueOf(category), name, new DateTime(date_of_manufacture),
                                new DateTime(date_of_admission),
                                new DateTime(date_fitness), Integer.valueOf(count));
                warehouse.addProduct(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return warehouse;

        /*Pattern p = Pattern.compile(WAREHOUSE_PATTERN);
        Matcher m = p.matcher(stLine);
        if (m.find()) {
            w_house.setName(m.group(2));
        }

        while (sc.hasNextLine()) {
            Product P = new Product();
            stLine = sc.nextLine();
            if (stLine.contains("-")) stLine = sc.nextLine();

            int prod_number = 0;
            p = Pattern.compile(PRODUCT_NUMBER_PATTERN);
            m = p.matcher(stLine);
            if (m.find()) prod_number = Integer.parseInt(m.group(2));

            stLine = sc.nextLine();
            p = Pattern.compile(PRODUCT_PATTERN);
            m = p.matcher(stLine);
            if (m.find()) {
                P.setName(m.group(2));
            }
            stLine = sc.nextLine();
            p = Pattern.compile(CATEGORY_PATTERN);
            m = p.matcher(stLine);
            if (m.find()) {
                P.setCategory(Product_Category.valueOf(m.group(2)));
            }
            stLine = sc.nextLine();
            p = Pattern.compile(DATE_PATTERN);
            m = p.matcher(stLine);
            if (m.find()) {
                P.setDate_of_manufacture(new DateTime(m.group(2)));
            }
            stLine = sc.nextLine();
            p = Pattern.compile(DATE_PATTERN);
            m = p.matcher(stLine);
            if (m.find()) {
                P.setDate_of_admission(new DateTime(m.group(2)));
            }
            stLine = sc.nextLine();
            p = Pattern.compile(DATE_PATTERN);
            m = p.matcher(stLine);
            if (m.find()) {
                P.setDate_fitness(new DateTime(m.group(2)));
            }
            //w_house.setProducts(P);
        }
        sc.close();
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return w_house;*/
    }


}
