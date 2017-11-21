package com.oxilo.oioindia.repositary.login;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.oioindia.data.AuthenticationManager;
import com.oxilo.oioindia.modal.User;
import com.oxilo.oioindia.repositary.login.exception.LoginTechFailureException;

import org.json.JSONObject;

import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nikk on 2/10/17.
 */
public class LoginAPIService {

    private ILoginAPI loginAPI;
    private boolean isRequestingLogin;
    private AuthenticationManager authenticationManager;

    public LoginAPIService(Retrofit retrofit, AuthenticationManager authenticationManager) {

        this.loginAPI = retrofit.create(ILoginAPI.class);
        this.authenticationManager = authenticationManager;
    }

    public boolean isRequestingWeather() {
        return isRequestingLogin;
    }

    public Maybe<Response<ResponseBody>> login(LoginRequest request) {
       return   loginAPI.login(request.getEmail(), request.getPassword())
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .onErrorResumeNext(this::handleLoginError)
                .doOnNext(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                        processWeatherResponse(responseBodyResponse);
                    }
                }).singleElement();



    }

    private Observable<Response<ResponseBody>> handleLoginError(Throwable throwable) {

        throw new LoginTechFailureException();
    }

    private void processWeatherResponse(Response<ResponseBody> loginResponse) {


        Object l = loginResponse;
        authenticationManager.logUserIn();
    }
}
