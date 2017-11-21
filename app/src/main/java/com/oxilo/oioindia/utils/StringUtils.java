package com.oxilo.oioindia.utils;

/**
 * Created by nikk on 18/10/17.
 */

public class StringUtils {
    public static String EMPTY = "";

    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String string) {
        return !isNotNullOrEmpty(string);
    }

    public static boolean isNotNullOrEmpty(String string) {
        return string != null && !string.equals(EMPTY);
    }
}
