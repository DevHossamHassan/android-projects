package com.lets_go_to_perfection.aast_todo.utils;

import android.util.Log;

import com.lets_go_to_perfection.aast_todo.BuildConfig;

/**
 * Created by Hossam on 12/10/2016.
 */

public class UtilsLogger {
    private final static String TAG = "AAST-TODO-TAG";

    private UtilsLogger() {
    }

    public static void d(String msg) {
        d(msg, null);
    }

    public static void d(String msg, Exception e) {
        if (BuildConfig.DEBUG) {
            if (e == null) {
                Log.d(TAG, "d: " + msg);
            } else {
                Log.d(TAG, "d: " + msg + "\nE: " + e.getMessage());
            }
        }
    }


    public static void e(String msg) {
        e(msg, null);
    }

    public static void e(String msg, Exception e) {
        if (BuildConfig.DEBUG) {
            if (e == null) {
                Log.e(TAG, "d: " + msg);
            } else {
                Log.e(TAG, "d: " + msg + "\nE: " + e.getMessage());
            }
        }
    }
}
