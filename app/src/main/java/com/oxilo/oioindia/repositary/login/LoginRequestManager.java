package com.oxilo.oioindia.repositary.login;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.oioindia.data.AuthenticationManager;
import com.oxilo.oioindia.modal.User;
import com.oxilo.oioindia.repositary.login.exception.LoginInternalException;
import com.oxilo.oioindia.retrofit.RetrofitFactory;

import org.json.JSONObject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nikk on 2/10/17.
 */
public class LoginRequestManager {

    private static LoginRequestManager instance;

    private AuthenticationManager responseManager;

    private LoginAPIService loginAPIService;

    private LoginRequestManager(Context context) {

        this.responseManager = AuthenticationManager.getInstance();

        Retrofit retrofit = null;
        try {
            retrofit = RetrofitFactory.getAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.loginAPIService = new LoginAPIService(retrofit, responseManager);
    }

    public static LoginRequestManager getInstance(Context context) {

        synchronized (LoginRequestManager.class) {
            if (instance == null) {
                instance = new LoginRequestManager(context);
            }

            return instance;
        }
    }



    public Maybe<Response<ResponseBody>> login(String email, String password) {
        return loginAPIService.login(createLoginRequest(email,password));
//                .map(responseBodyResponse -> makeTempRequest(responseBodyResponse));
    }


    private User makeTempRequest(Response<ResponseBody> weatherResponse) {
        User main = null;
        try {
            String sd = new String(weatherResponse.body().bytes());
            JSONObject mapping = new JSONObject(sd);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            main = mapper.readValue(mapping.getString("main"), new TypeReference<User>() {
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return main;
    }

    private LoginRequest createLoginRequest(String email,String pwd) {

//        String email = "a@a.com";//responseManager.getEmail();
//        String pwd = "12";//responseManager.getPassword();

        if (email == null || email.isEmpty() ||
                pwd == null || pwd.isEmpty()) {
            throw new LoginInternalException();
        }

        return new LoginRequest(email, pwd);
    }


}
