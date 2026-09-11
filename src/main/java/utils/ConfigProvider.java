package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private Properties properties;
    private static ConfigProvider instance;

    private ConfigProvider() {
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (Exception e) {
            AllureLogger.logToAllure("Config Load Error", "Could not load properties: " + e.getMessage());
            throw new RuntimeException("Could not download application.properties");
        }
    }
    public static ConfigProvider getInstance() {
        if (instance == null) {
            instance = new ConfigProvider();
        }
        return instance;
    }


    public String getPassword() {
        return properties.getProperty("test.password");
    }

    public String getBaseUrl() {
        return properties.getProperty("test.base.url");
    }
}
