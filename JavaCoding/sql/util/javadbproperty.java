package util;

import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String fileName) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(fileName));
        return props.getProperty("db.url");
    }
}
