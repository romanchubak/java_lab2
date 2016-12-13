package DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by romanchubak on 30.11.2016.
 */
public  class PropertyManger {
    public static Properties getProperties() {
        String config = "db.config";
        config = System.getProperty("db.config", config );
        Properties properties = new Properties( );
        try {
            FileInputStream fis = new FileInputStream( config );
            properties.load( fis );
            fis.close( );
         } catch ( java.io.IOException e ) {
            e.printStackTrace();
        }
        return properties;

    }

}
