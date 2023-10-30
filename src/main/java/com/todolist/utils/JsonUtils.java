package com.todolist.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class JsonUtils {
    public static <T> T parseJsonFile(String filePath, Type type) throws JsonParseException, FileNotFoundException {
        try (FileReader fileReader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(fileReader, type);
        } catch (JsonParseException | FileNotFoundException e) {
            throw new RuntimeException("Error parsing JSON file: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("IO error while reading file: " + e.getMessage(), e);
        }
    }
    }

