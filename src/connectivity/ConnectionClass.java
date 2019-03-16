package connectivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    public static Connection connection;

    public static Connection getConnection() {

        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite::src/zoonote.db");

        } catch (Exception e) {
        }
        System.out.println("Opened database successfully");
        return connection;

    }
}
