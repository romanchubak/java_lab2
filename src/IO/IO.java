package IO;

/**
 * Created by romanchubak on 01.11.2016.
 */

import Classes.Warehouse;
public interface IO
{
     void WriteToFile(Warehouse w_house, String fileName );
     Warehouse  ReadFromFile(String fileName);
}
