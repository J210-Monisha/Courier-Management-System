package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
    private static Connection connection;

    @SuppressWarnings("exports")
	public static Connection getConnection(String propertiesFileName) {
        if (connection == null) {
            try {
                Properties props = DBPropertyUtil.getConnectionProperties(propertiesFileName);
                String driver = props.getProperty("db.driver");
                String url = props.getProperty("db.url");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Database connection failed: " + e.getMessage());
            }
        }
        return connection;
    }
}
