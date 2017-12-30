package com.oxilo.oioindia;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by nikk on 9/2/17.
 */

public class AppPrefs {
    private static AppPrefs appPrefs;
    private final Context context;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor  editor;
    private static Gson GSON            = new Gson();
    Type typeOfObject = new TypeToken<Object>(){}.getType();

    private AppPrefs(Context context, String namePreferences, int mode) {
        this.context = context;
        if (namePreferences == null || namePreferences.equals("")) {
            namePreferences = "oio";
        }
        preferences = context.getSharedPreferences(namePreferences, mode);
        editor = preferences.edit();
    }

    public static AppPrefs getComplexPreferences(Context context,
                                                      String namePreferences, int mode) {
        if (appPrefs == null) {
            appPrefs = new AppPrefs(context,
                    namePreferences, mode);
        }
        return appPrefs;
    }

    public void putObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object is null");
        }
        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("Key is empty or null");
        }
        editor.putString(key, GSON.toJson(object));
    }
    public void commit() {
        editor.commit();
    }

    public void clear(){
        editor.clear();
        commit();
    }

    public <T> T getObject(String key, Class<T> a) {
        String gson = preferences.getString(key, null);
        if (gson == null) {
            return null;
        }
        else {
            try {
                return GSON.fromJson(gson, a);
            }
            catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key "
                        + key + " is instance of other class");
            }
        }
    }


}
