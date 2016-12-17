package com.lets_go_to_perfection.aast_todo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Hossam on 12/10/2016.
 */

public class UtilsDateTime {
    public static String get() {
        SimpleDateFormat output = new SimpleDateFormat("dd-MMM HH:mm", Locale.US);
        Date date = new Date();
        return output.format(date);
    }
}
