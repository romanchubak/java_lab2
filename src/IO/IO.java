package IO;

/**
 * Created by romanchubak on 01.11.2016.
 */

import Entity.Warehouse;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IO
{
     void WriteToFile(Warehouse w_house, String fileName )
             throws IOException;
     Warehouse readFromFile(String fileName)
             throws FileNotFoundException;
}
