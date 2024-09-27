package com.school.demo.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    // Utility method to return current time in yyyyMMddHHmmss format
    public static String getCurrentTimeStamp() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
}
