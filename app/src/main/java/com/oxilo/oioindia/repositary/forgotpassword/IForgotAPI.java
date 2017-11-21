package com.oxilo.oioindia.repositary.forgotpassword;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nikk on 2/10/17.
 */
public interface IForgotAPI {

    @FormUrlEncoded
    @POST("api/forgot-password-otp.php")
    Observable<Response<ResponseBody>> forgot(
            @Field("mobileno") String mobile);

    @FormUrlEncoded
    @POST("api/forgot-password-otp-match.php")
    Observable<Response<ResponseBody>> otpMatch(
            @Field("mobileno") String mobile,
            @Field("otp") String otp);

    @FormUrlEncoded
    @POST("api/forgot-password-reset.php")
    Observable<Response<ResponseBody>> resetPassword(
            @Field("userID") String userid,
            @Field("password") String password,
            @Field("cpassword") String cpassword);
}
