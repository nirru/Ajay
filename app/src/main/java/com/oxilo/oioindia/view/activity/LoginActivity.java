package com.oxilo.oioindia.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.oxilo.oioindia.AppController;
import com.oxilo.oioindia.R;
import com.oxilo.oioindia.databinding.ActivityLoginBinding;
import com.oxilo.oioindia.utils.FormUtils;
import com.oxilo.oioindia.utils.RxUtils;
import com.oxilo.oioindia.utils.StringUtils;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.view.common.*;
import com.oxilo.oioindia.viewmodal.LoginViewModal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import io.reactivex.Observable;

/**
 * Created by nikk on 14/10/17.
 */

public class LoginActivity extends com.oxilo.oioindia.view.common.BaseActivity implements CallAnotherActivityNavigator {

    ActivityLoginBinding binding;
    LoginViewModal loginViewModal;

    public CallbackManager callbackManager;
    private String city;
    private String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent()!=null){
             city = getIntent().getStringExtra("CITY");
             address = getIntent().getStringExtra("ADDRESS");
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModal.Factory factory = new LoginViewModal.Factory(getApplication(),this);
        loginViewModal = ViewModelProviders.of(this,factory).get(LoginViewModal.class);
        binding.setViewModel(loginViewModal);

        RxUtils.toObservable(loginViewModal.email).map(s -> FormUtils.checkEmail(s) ? null : "Invalid Email")
                .subscribe(s -> binding.email.setError(s), throwable -> throwable.printStackTrace());
        RxUtils.toObservable(loginViewModal.password).map(s -> FormUtils.checkPassword(s) ? null : "Invalid Password")
                .subscribe(s -> binding.password.setError(s), throwable -> throwable.printStackTrace());

        Observable.combineLatest(RxUtils.toObservable(loginViewModal.email), RxUtils.toObservable(loginViewModal.password), (email, password) -> StringUtils.isNotNullOrEmpty(email) && StringUtils.isNotNullOrEmpty(password))
                .subscribe(result -> {
//                    binding.loginBtn.setEnabled(result);
                    loginViewModal.enable.set(result);
                    if (!result) {
                    }
                }, Throwable::printStackTrace);

    }

    @Override
    public void callActivity() {
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }

    @Override
    public void callActivity(int k) {

        if (k==1){
            getPd().dismiss();
            Toast.makeText(LoginActivity.this,"Login Sucessfull", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            i.putExtra("CITY", "" + city);
            i.putExtra("ADDRESS", "" + address);
            startActivity(i);
            finish();
        }else if (k==2){
            getPd().dismiss();
            Toast.makeText(LoginActivity.this,"Login Failure", Toast.LENGTH_SHORT).show();
        }
        else if (k==3){
           getPd().show();
        }
        else if (k==4){
            Intent i = new Intent(LoginActivity.this,ForgetActivity.class);
            startActivity(i);
        }
        else if (k==5){
            fb();
        }
        else if (k==6){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (callbackManager !=null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void fb(){
//        loginButton.setReadPermissions("email");
        // Callback registration
        callbackManager = CallbackManager.Factory.create();
        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email","user_photos","public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("JDED==","" + loginResult.getAccessToken().toString());
                getUserDetailsFromFB(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getApplicationContext(),"fb user canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getApplicationContext(),"fb error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUserDetailsFromFB(AccessToken accessToken) {

        GraphRequest req=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Toast.makeText(getApplicationContext(),"graph request completed", Toast.LENGTH_SHORT).show();
                try{
                    AppController.getInstance().getAppPrefs().putObject("LOGIN","1");
                    AppController.getInstance().getAppPrefs().commit();
                    String email =  object.getString("email");
                    String name = object.getString("name");
                    String id = object.getString("id");
                    String photourl =object.getJSONObject("picture").getJSONObject("data").getString("url");

                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(),"graph request error : "+e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday,picture.type(large)");
        req.setParameters(parameters);
        req.executeAsync();
    }
}
