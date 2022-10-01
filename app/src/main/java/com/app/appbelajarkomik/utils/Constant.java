package com.app.appbelajarkomik.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Constant {
    public static SharedPreferences sharedPreferences;
    private static final String PREF ="pref";


    public static String idLogin = "idLogin";

    public static String isFirstTime = "isFirstTime";


    public static void setIsFirstTime(Context context, boolean value){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(isFirstTime, value);
        editor.commit();
        editor.apply();
    }

    public static boolean getIsFirstTime(Context context){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(isFirstTime, true);
    }





    public static void setId(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(idLogin, value);
        editor.commit();
        editor.apply();
    }

    public static String getId(Context context){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(idLogin, null);
    }
}
