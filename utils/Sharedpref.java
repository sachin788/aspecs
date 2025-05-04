package com.example.aspecs.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharedpref {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String PREF_NAME ="user_prefs";

    public Sharedpref(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME,0);
        editor = sharedPreferences.edit();
    }
    public void setString(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }
    public Boolean checkKeyExists(String key) {
        return sharedPreferences.contains(key);
    }
    public void clearData() {
        editor.clear().apply();
    }
}
