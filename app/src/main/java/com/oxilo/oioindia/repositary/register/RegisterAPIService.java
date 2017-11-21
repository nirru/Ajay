package com.oxilo.oioindia.repositary.register;


import com.oxilo.oioindia.data.AuthenticationManager;
import com.oxilo.oioindia.data.PrivateSharedPreferencesManager;
import com.oxilo.oioindia.repositary.login.exception.LoginTechFailureException;
import com.oxilo.oioindia.repositary.register.exception.RegistrationNicknameAlreadyExistsException;
import com.oxilo.oioindia.repositary.register.exception.RegistrationTechFailureException;

import org.json.JSONObject;

import java.net.HttpRetryException;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nikk on 2/10/17.
 */
public class RegisterAPIService {

    private IRegisterAPI registerAPI;
    private boolean isRequestingLogin;
    private PrivateSharedPreferencesManager privateSharedPreferencesManager;

    public RegisterAPIService(Retrofit retrofit, PrivateSharedPreferencesManager privateSharedPreferencesManager) {

        this.registerAPI = retrofit.create(IRegisterAPI.class);
        this.privateSharedPreferencesManager = privateSharedPreferencesManager;
    }

    public boolean isRequestingWeather() {
        return isRequestingLogin;
    }

    public Completable register (RegisterRequest request) {
        return registerAPI.register(request.getFname(), request.getLname(),request.getEmail(),request.getPassword(),request.getMobile())
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleRegistrationError)
                .doOnNext(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> registrationResponse) throws Exception {
                       System.out.println(registrationResponse);
                       Object s = registrationResponse;
                        RegisterAPIService.this.processRegistrationResponse(request, registrationResponse);

                    }
                })
                .ignoreElements();
    }

    private Observable<Response<ResponseBody>> handleRegistrationError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new RegistrationNicknameAlreadyExistsException();
            } else {
                throw new RegistrationTechFailureException();
            }

        } else {
            throw new RegistrationTechFailureException();
        }
    }

    private void processRegistrationResponse(RegisterRequest registrationRequest, Response<ResponseBody> registrationResponse) {

        privateSharedPreferencesManager.storeUserNickname(registrationRequest.getFname());
    }
}
