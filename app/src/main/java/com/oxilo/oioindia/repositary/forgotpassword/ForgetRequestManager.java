package com.oxilo.oioindia.repositary.forgotpassword;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.oioindia.data.AuthenticationManager;
import com.oxilo.oioindia.modal.User;
import com.oxilo.oioindia.repositary.forgotpassword.exception.ForgotInternalException;
import com.oxilo.oioindia.retrofit.RetrofitFactory;

import org.json.JSONObject;

import io.reactivex.Maybe;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nikk on 2/10/17.
 */
public class ForgetRequestManager {

    private static ForgetRequestManager instance;

    private AuthenticationManager responseManager;

    private ForgotAPIService forgotAPIService;

    private ForgetRequestManager(Context context) {

        this.responseManager = AuthenticationManager.getInstance();

        Retrofit retrofit = null;
        try {
            retrofit = RetrofitFactory.getAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.forgotAPIService = new ForgotAPIService(retrofit, responseManager);
    }

    public static ForgetRequestManager getInstance(Context context) {

        synchronized (ForgetRequestManager.class) {
            if (instance == null) {
                instance = new ForgetRequestManager(context);
            }

            return instance;
        }
    }



    public Maybe<Response<ResponseBody>> forget(String mobile) {
        return forgotAPIService.forget(createForgetRequest(mobile));
    }

    public Maybe<Response<ResponseBody>> otpVarification(String mobile,String otp) {
        return forgotAPIService.otpVerify(createOtpRequest(mobile,otp));
    }

    public Maybe<Response<ResponseBody>> resetPassword(String userid,String password,String cpassword) {
        return forgotAPIService.otpVerify(createResetPasswordRequest(userid,password,cpassword));
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

    private ForgetRequest createForgetRequest(String mobile) {

//        String email = "a@a.com";//responseManager.getEmail();
//        String pwd = "12";//responseManager.getPassword();

        if (mobile == null ) {
            throw new ForgotInternalException();
        }

        return new ForgetRequest(mobile);
    }


    private ForgetRequest createOtpRequest(String mobile,String otp) {

//        String email = "a@a.com";//responseManager.getEmail();
//        String pwd = "12";//responseManager.getPassword();

        if (mobile == null ) {
            throw new ForgotInternalException();
        }

        return new ForgetRequest(mobile,otp);
    }

    private ForgetRequest createResetPasswordRequest(String userID,String password,String cPassword) {

//        String email = "a@a.com";//responseManager.getEmail();
//        String pwd = "12";//responseManager.getPassword();

        if (userID == null || password == null || cPassword == null ) {
            throw new ForgotInternalException();
        }

        return new ForgetRequest(userID,password,cPassword);
    }

}
