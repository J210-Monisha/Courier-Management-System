package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnutil {
    public static Connection getConnection(String fileName) {
        Connection conn = null;
        try (InputStream input = DBConnutil.class.getClassLoader().getResourceAsStream(fileName)) {
            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("url");
            String user = prop.getProperty("username");
            String pass = prop.getProperty("password");

            conn = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}