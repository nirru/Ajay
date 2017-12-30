package com.oxilo.oioindia;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by nikk on 29/12/17.
 */

public class AppController extends Application{

    private AppPrefs appPrefs;
    private static AppController mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
        mInstance = this;
        appPrefs = AppPrefs.getComplexPreferences(getBaseContext(), "ipif prefs", MODE_PRIVATE);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public AppPrefs getAppPrefs() {
        if(appPrefs != null) {
            return appPrefs;
        }
        return null;
    }
}
