package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.Class.forName;

/**
 * Created by romanchubak on 30.11.2016.
 */
public class ConnectionManager {
    private static Connection connection = null;
    private static Connection  makeConnection( ) {
        Properties properties = PropertyManger.getProperties();

        String jdbc_driver = properties.getProperty("jdbc.drivers");
        String url = properties.getProperty("jdbc.url");

        try {
            Class.forName(jdbc_driver);
            connection =
                    DriverManager.getConnection(url, properties);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection( ) {
        if ( connection == null ) connection = makeConnection();
        return connection;
    }
    public static void close() {
        if ( connection != null ) try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

