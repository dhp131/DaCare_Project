package com.prm392.dacare.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    private static final String PREF_NAME = "MyAppPrefs";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    public static void saveAccessToken(String token) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, token).apply();
    }

    public static String getAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

    public static void clearAccessToken() {
        sharedPreferences.edit().remove(KEY_ACCESS_TOKEN).apply();
    }

    public static void put(String key, String value) {
        sharedPreferences.edit().putString(key,value).apply();
    }
}
