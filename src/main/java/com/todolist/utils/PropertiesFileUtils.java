package com.todolist.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
    private PropertiesFileUtils() {}

    public static Properties loadPropertiesFromFile(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileReader input = new FileReader(System.getProperty("user.dir") + filePath)) {
            // Load properties from the file
            properties.load(input);
            return properties;
        }
    }

    public static void appendSystemProperties(Properties properties) {
        for (String name : properties.stringPropertyNames()) {
            String value = properties.getProperty(name);
            System.setProperty(name, value);
        }
    }
}
