package com.oxilo.oioindia.viewmodal;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.oioindia.AppController;
import com.oxilo.oioindia.R;
import com.oxilo.oioindia.repositary.login.LoginRequestManager;
import com.oxilo.oioindia.utils.FormUtils;
import com.oxilo.oioindia.utils.RxUtils;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.view.activity.LoginActivity;
import com.oxilo.oioindia.view.activity.RegisterActivity;
import com.oxilo.oioindia.vo.Login;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.functions.Func1;


/**
 * Created by nikk on 13/10/17.
 */

public class LoginViewModal extends AndroidViewModel {
    Application application;
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableBoolean enable = new ObservableBoolean();
    private CallAnotherActivityNavigator navigator;

    public LoginViewModal( Application application,CallAnotherActivityNavigator navigator) {
        super(application);
        this.application = application;
        this.navigator = navigator;
    }

    public void onLogin(){
        navigator.callActivity(3);
        LoginRequestManager.getInstance(application.getApplicationContext()).login(email.get(),password.get()).subscribe(new Consumer<Response<ResponseBody>>() {
            @Override
            public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                try
                {
                    String sd = new String(responseBodyResponse.body().bytes());
                    JSONObject mapping = new JSONObject(sd);
                    if (mapping.getString("message").equals("Success Login")){
                        navigator.callActivity(1);
                        AppController.getInstance().getAppPrefs().putObject("LOGIN","1");
                        AppController.getInstance().getAppPrefs().commit();
                    }else{
                        navigator.callActivity(2);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    navigator.callActivity(2);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                navigator.callActivity(2);
            }
        });
    }

    public void doFb(){
        navigator.callActivity(5);
    }

    public void skip(){
        navigator.callActivity(6);
    }


    public void launchRegisterActivity(){
        navigator.callActivity();
    }
    public void launchForgotActivity(){
        navigator.callActivity(4);
    }



    public void onProfileImageClicked(View view) {
        Log.e("ViewModel", "profile Clicked");
    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        CallAnotherActivityNavigator navigator;

        public Factory( Application application,CallAnotherActivityNavigator navigator) {
            mApplication = application;
            this.navigator = navigator;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new LoginViewModal(mApplication,navigator);
        }
    }
}
