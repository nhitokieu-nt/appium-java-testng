package com.todolist.utils;

import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParseException;
//import org.openqa.selenium.json.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class JsonUtils {
//    public static JsonObject readJsonFile(String filePath) throws Exception {
//        Convert JSON File to Java JsonObject
//        Type jsonObjectType = new TypeToken<JsonObject>() {}.getType();
//        JsonObject jsonObject;
//        Gson gson = new Gson();
//        JsonReader jsonReader;
//        try {
//            jsonReader = new JsonReader(new FileReader(filePath));
//            jsonObject = gson.fromJson(jsonReader, jsonObjectType);
//        } catch (Exception e) {
//            throw new Exception("Can't parsing file to jsonObject", e);
//        }
//        return jsonObject;
//        public static <T> T parseJsonFile(String filePath, Type type) throws Exception {
//            Gson gson = new Gson();
//            FileReader fileReader = new FileReader(filePath);
//            return gson.fromJson(fileReader, type);
//        }

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

