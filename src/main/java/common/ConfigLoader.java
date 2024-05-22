package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;

    static {
        try (FileInputStream input = new FileInputStream("src/test/resources/properties/configure.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file.");
        }
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
