package IO;

import Entity.Warehouse;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by romanchubak on 02.11.2016.
 */
public class JsonIO  implements  IO {

    @Override
    public void WriteToFile(Warehouse w_house, String fileName) {
        try  {
            FileWriter file = new FileWriter(fileName);
            file.write(new Gson().toJsonTree(w_house).toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Warehouse readFromFile(String fileName) {
        try {
            return new Gson()
                    .fromJson(new JsonParser().parse(new FileReader(fileName)), Warehouse.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
