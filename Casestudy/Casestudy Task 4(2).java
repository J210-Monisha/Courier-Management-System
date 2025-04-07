package util;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBPropertyUtil {
    public static Properties getConnectionProperties(String fileName) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Error reading db properties file: " + e.getMessage());
        }
        return props;
    }
    public static void main(String[] args) {
        Connection conn = DBConnUtil.getConnection("db.properties");
        if (conn != null) {
            System.out.println("Connection established!");
        } else {
            System.out.println("Failed to connect.");
        }
    }
}