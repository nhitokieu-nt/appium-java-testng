package com.todolist.utils;

import java.io.File;
import java.net.URL;

public class Common {
    public static String getFullPath(String filePath) {
        ClassLoader classLoader = Common.class.getClassLoader();
        URL resourceUrl = classLoader.getResource(filePath);

        if (resourceUrl != null) {
            File file;
            try {
                file = new File(resourceUrl.toURI());
                return file.getAbsolutePath();
            } catch (Exception e) {
                throw new RuntimeException("Error converting resource URL to file path: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("Resource not found: " + filePath);
        }
    }
}
