package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static Properties properties;

    static {
        try (InputStream input = ConfigProvider.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (Exception e) {
            AllureLogger.logToAllure("Config Load Error", "Could not load properties: " + e.getMessage());
            throw new RuntimeException("Could not download application.properties");
        }
    }

    public static String getPassword() {
        return properties.getProperty("test.password");
    }

    public static String getBaseUrl() {
        return properties.getProperty("test.base.url");
    }
}
