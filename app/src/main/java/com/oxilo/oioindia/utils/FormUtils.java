package com.oxilo.oioindia.utils;

import android.text.TextUtils;

/**
 * Created by nikk on 18/10/17.
 */

public class FormUtils {

    public static boolean checkEmail(String target){
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean checkPassword(String target){
        return !TextUtils.isEmpty(target);
    }

    public static boolean checkPhone(String target){
        return !TextUtils.isEmpty(target) && target.length()==10;
    }

}
