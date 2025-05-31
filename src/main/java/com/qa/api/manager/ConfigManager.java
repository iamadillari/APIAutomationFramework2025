package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigManager is a utility class for managing application configuration properties.
 * It provides methods to retrieve and set properties from a configuration file.
 */
public class ConfigManager {

    // A static Properties object to hold the configuration properties
    private static Properties properties = new Properties();

    // Static block to load properties from the configuration file at class initialization
    static {
        InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties");
        if (input != null) {
            try {
                // Load properties from the input stream
                properties.load(input);
            } catch (IOException e) {
                // Print the stack trace if an IOException occurs
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves the value of a property by its key.
     *
     * @param key The key of the property to retrieve.
     * @return The value of the property, or null if the key does not exist.
     */
    public static String get(String key) {
        return properties.getProperty(key).trim();
    }

    /**
     * Sets the value of a property.
     *
     * @param key   The key of the property to set.
     * @param value The value to associate with the key.
     */
    public static void set(String key, String value) {
        properties.setProperty(key, value);
    }

}