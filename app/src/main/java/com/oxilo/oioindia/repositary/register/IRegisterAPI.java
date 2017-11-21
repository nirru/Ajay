package com.oxilo.oioindia.repositary.register;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nikk on 2/10/17.
 */
public interface IRegisterAPI {

    @FormUrlEncoded
    @POST("api/register.php")
    Observable<Response<ResponseBody>> register(
            @Field("first_name") String fname,
            @Field("last_name") String lname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobileno") String mobile);
}
