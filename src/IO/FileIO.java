package IO;

/**
 * Created by romanchubak on 01.11.2016.
 */
import java.io.*;

import java.util.Scanner;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import Classes.*;


public class FileIO implements IO
{
    private static String WAREHOUSE_PATTERN = "(>Warehouse: )([A-Z][a-z]+)(\\s)";

    private static String PRODUCT_PATTERN = "(>>Product: )([A-Z][a-z]+)(\\s)";

    private static String CATEGORY_PATTERN = "(>>>Category: )([A-Z]+)(\\s)";

    private static String DATE_PATTERN = "(>>>\\w+: )(20[1-9][0-9]-[01][012]-[0123][0-9]\\s[012][0-9]:[0-5][0-9]:[0-6][0-9])(\\s)";

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

    public Warehouse  ReadFromFile(String fileName)
    {
        Scanner sc = null;
        FileReader fr = null;

        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc = new Scanner(fr);
        Warehouse w_house = new Warehouse();
        return w_house;
    }



}
