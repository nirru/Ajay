package com.oxilo.oioindia.repositary.register;

import android.content.Context;

import com.oxilo.oioindia.data.PrivateSharedPreferencesManager;
import com.oxilo.oioindia.repositary.register.exception.RegistrationInternalException;
import com.oxilo.oioindia.retrofit.RetrofitFactory;

import io.reactivex.Completable;
import retrofit2.Retrofit;

/**
 * Created by nikk on 2/10/17.
 */
public class RegisterRequestManager {

    private static RegisterRequestManager instance;

    private PrivateSharedPreferencesManager privateSharedPreferencesManager;

    private RegisterAPIService registerAPIService;

    private RegisterRequestManager(Context context) {

        this.privateSharedPreferencesManager = PrivateSharedPreferencesManager.getInstance(context);

        Retrofit retrofit = null;
        try {
            retrofit = RetrofitFactory.getAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.registerAPIService = new RegisterAPIService(retrofit, privateSharedPreferencesManager);
    }

    public static RegisterRequestManager getInstance(Context context) {

        synchronized (RegisterRequestManager.class) {
            if (instance == null) {
                instance = new RegisterRequestManager(context);
            }

            return instance;
        }
    }



    public Completable register(String fname, String lname, String email, String pwd, String mobile) {
        return registerAPIService.register(createRegisterRequest(fname,lname,email,pwd,mobile));
//                .map(responseBodyResponse -> makeTempRequest(responseBodyResponse));
    }
    private RegisterRequest createRegisterRequest(String fname,String lname,String email,String pwd,String mobile) {

//        String email = "a@a.com";//responseManager.getEmail();
//        String pwd = "12";//responseManager.getPassword();

        if (fname == null || fname.isEmpty() ||
                lname == null || lname.isEmpty() ||email == null || email.isEmpty() ||
                pwd == null || pwd.isEmpty() || mobile == null || mobile.isEmpty()) {
            throw new RegistrationInternalException();
        }

        return new RegisterRequest(fname,lname,email, pwd,mobile);
    }


}
