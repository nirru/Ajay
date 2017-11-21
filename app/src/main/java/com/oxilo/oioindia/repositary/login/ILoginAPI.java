package com.oxilo.oioindia.repositary.login;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by nikk on 2/10/17.
 */
public interface ILoginAPI {

    @FormUrlEncoded
    @POST("api/login.php")
    io.reactivex.Observable<Response<ResponseBody>> login(
            @Field("email") String email,
            @Field("password") String password);
}
