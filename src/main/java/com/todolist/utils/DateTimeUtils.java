package com.todolist.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    public static String convertDateTime(String inputFormat, String outputFormat, String dateStr) {
        try {
            SimpleDateFormat inputFormatter = new SimpleDateFormat(inputFormat);
            Date date = inputFormatter.parse(dateStr);

            SimpleDateFormat outputFormatter = new SimpleDateFormat(outputFormat);
            String outputDateStr = outputFormatter.format(date);

            return outputDateStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
