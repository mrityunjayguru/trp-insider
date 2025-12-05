package helloworld;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String host = "13.203.239.58";
        String dbName = "trpinsiderdb";
        String user = "postgres";
        String password = "traccar@1234";
        String port = "5432";

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        return DriverManager.getConnection(url, user, password);
    }
}
