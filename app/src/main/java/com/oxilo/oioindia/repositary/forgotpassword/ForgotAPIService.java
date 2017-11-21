package com.oxilo.oioindia.repositary.forgotpassword;


import com.oxilo.oioindia.data.AuthenticationManager;
import com.oxilo.oioindia.repositary.forgotpassword.exception.ForgetTechFailureException;

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
public class ForgotAPIService {

    private IForgotAPI iForgotAPI;
    private boolean isRequestingLogin;
    private AuthenticationManager authenticationManager;

    public ForgotAPIService(Retrofit retrofit, AuthenticationManager authenticationManager) {

        this.iForgotAPI = retrofit.create(IForgotAPI.class);
        this.authenticationManager = authenticationManager;
    }

    public boolean isRequestingWeather() {
        return isRequestingLogin;
    }

    public Maybe<Response<ResponseBody>> forget(ForgetRequest forgot) {
       return   iForgotAPI.forgot(forgot.getMobile())
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .onErrorResumeNext(this::handleForgetError)
                .doOnNext(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                        processWeatherResponse(responseBodyResponse);
                    }
                }).singleElement();
    }

    public Maybe<Response<ResponseBody>> otpVerify(ForgetRequest forgot) {
        return   iForgotAPI.otpMatch(forgot.getMobile(),forgot.getOtp())
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleForgetError)
                .doOnNext(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                        processWeatherResponse(responseBodyResponse);
                    }
                }).singleElement();
    }

    public Maybe<Response<ResponseBody>> resetPassword(ForgetRequest forgot) {
        return   iForgotAPI.resetPassword(forgot.getUserID(),forgot.getPassword(),forgot.getcPassword())
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleForgetError)
                .doOnNext(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                        processWeatherResponse(responseBodyResponse);
                    }
                }).singleElement();
    }

    private Observable<Response<ResponseBody>> handleForgetError(Throwable throwable) {

        throw new ForgetTechFailureException();
    }

    private void processWeatherResponse(Response<ResponseBody> loginResponse) {


        Object l = loginResponse;
        authenticationManager.logUserIn();
    }
}
