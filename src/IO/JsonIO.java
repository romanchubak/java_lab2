package IO;
import com.*;

import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * Created by romanchubak on 01.11.2016.
 */
public class JsonIO implements IO {

    @Override
    public void WriteToFile(Classes.Warehouse w_house, String fileName )
    {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileName + ".json");
        Warehause W = new Warehause(w_house);

        try {
            mapper.writeValue(file, W);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Classes.Warehouse ReadFromFile(String fileName)
    {

            ObjectMapper mapper = new ObjectMapper();
            File file = new File(fileName + ".json");
            Warehause W = new Warehause();

            try {
                W = mapper.readValue(file, Warehause.class);
            } catch (IOException e) {

                e.printStackTrace();
            }
            return new Classes.Warehouse(W);
    }
}
