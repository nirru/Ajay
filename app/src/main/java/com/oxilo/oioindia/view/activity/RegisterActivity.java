package com.oxilo.oioindia.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.databinding.ActivityRegisterBinding;
import com.oxilo.oioindia.utils.FormUtils;
import com.oxilo.oioindia.utils.RxUtils;
import com.oxilo.oioindia.utils.StringUtils;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.view.common.*;
import com.oxilo.oioindia.viewmodal.RegitrationViewModal;

import io.reactivex.Observable;

public class RegisterActivity extends com.oxilo.oioindia.view.common.BaseActivity implements CallAnotherActivityNavigator {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        RegitrationViewModal.Factory factory = new RegitrationViewModal.Factory(getApplication(),this);
        RegitrationViewModal regitrationViewModal = ViewModelProviders.of(this,factory).get(RegitrationViewModal.class);
        binding.setViewModel(regitrationViewModal);

        RxUtils.toObservable(regitrationViewModal.name).map(s -> FormUtils.checkPassword(s) ? null : "Name must not br empty")
                .subscribe(s -> binding.name.setError(s), throwable -> throwable.printStackTrace());
        RxUtils.toObservable(regitrationViewModal.email).map(s -> FormUtils.checkEmail(s) ? null : "Invalid Email")
                .subscribe(s -> binding.email.setError(s), throwable -> throwable.printStackTrace());
        RxUtils.toObservable(regitrationViewModal.password).map(s -> FormUtils.checkPassword(s) ? null : "Invalid Password")
                .subscribe(s -> binding.password.setError(s), throwable -> throwable.printStackTrace());
        RxUtils.toObservable(regitrationViewModal.mobile).map(s -> FormUtils.checkPhone(s) ? null : "Phone number must be of 10 digits")
                .subscribe(s -> binding.mobile.setError(s), throwable -> throwable.printStackTrace());


        Observable.combineLatest(RxUtils.toObservable(regitrationViewModal.name),
                RxUtils.toObservable(regitrationViewModal.email), RxUtils.toObservable(regitrationViewModal.password),
                RxUtils.toObservable(regitrationViewModal.mobile), (name,email, password,mobile) -> StringUtils.isNotNullOrEmpty(name) && StringUtils.isNotNullOrEmpty(email) && StringUtils.isNotNullOrEmpty(password) && StringUtils.isNotNullOrEmpty(mobile))
                .subscribe(result -> {
                    binding.registerBtn.setEnabled(result);
                    regitrationViewModal.enable.set(result);
                    if (!result) {
                    }
                }, Throwable::printStackTrace);

    }

    @Override
    public void callActivity() {
        finish();
    }

    @Override
    public void callActivity(int k) {
        if (k == 1){
            getPd().dismiss();
            Intent i = new Intent(RegisterActivity.this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(i);
        }
        else if (k== 2){
            getPd().dismiss();
        } else if (k== 3){
            getPd().show();
        }

    }
}
