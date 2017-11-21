package com.oxilo.oioindia.vo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.oxilo.oioindia.view.activity.LoginActivity;
import com.oxilo.oioindia.view.activity.SplashActivity;


/**
 * Created by nikk on 13/10/17.
 */

public class Splash {

    public Splash(Context context) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                context.startActivity(new Intent(context, LoginActivity.class));
//            }
//        }, 2000);
    }

    public String get_img() {
        return _img;
    }

    public void set_img(String _img) {
        this._img = _img;
    }

    private String _img;

}
