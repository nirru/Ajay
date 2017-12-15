package com.oxilo.oioindia.view.activity;

import android.app.Activity;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.databinding.ActivityLoginBinding;
import com.oxilo.oioindia.utils.FormUtils;
import com.oxilo.oioindia.utils.RxUtils;
import com.oxilo.oioindia.utils.StringUtils;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.view.common.BaseActivity;
import com.oxilo.oioindia.viewmodal.LoginViewModal;

import io.reactivex.Observable;

/**
 * Created by nikk on 14/10/17.
 */

public class LoginActivity extends BaseActivity implements CallAnotherActivityNavigator{

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModal.Factory factory = new LoginViewModal.Factory(getApplication(),this);
        LoginViewModal loginViewModal = ViewModelProviders.of(this,factory).get(LoginViewModal.class);
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
            Toast.makeText(LoginActivity.this,"Login Sucessfull",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }else if (k==2){
            getPd().dismiss();
            Toast.makeText(LoginActivity.this,"Login Failure",Toast.LENGTH_SHORT).show();
        }
        else if (k==3){
           getPd().show();
        }
        else if (k==4){
            Intent i = new Intent(LoginActivity.this,ForgetActivity.class);
            startActivity(i);
        }
    }
}
