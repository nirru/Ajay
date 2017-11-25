package com.oxilo.oioindia.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.databinding.ActivityChangePasswordBinding;
import com.oxilo.oioindia.utils.FormUtils;
import com.oxilo.oioindia.utils.RxUtils;
import com.oxilo.oioindia.utils.StringUtils;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.viewmodal.ChangePasswordViewModal;
import com.oxilo.oioindia.viewmodal.LoginViewModal;

import io.reactivex.Observable;

public class ChangePasswordActivity extends AppCompatActivity implements CallAnotherActivityNavigator{

    ActivityChangePasswordBinding binding;
    ChangePasswordViewModal changePasswordViewModal;
    private String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        if (getIntent() != null){
            this.user_id = getIntent().getStringExtra("A");
        }
        ChangePasswordViewModal.Factory factory = new ChangePasswordViewModal.Factory(getApplication(),this,user_id);
        changePasswordViewModal = ViewModelProviders.of(this,factory).get(ChangePasswordViewModal.class);
        binding.setViewModel(changePasswordViewModal);

        RxUtils.toObservable(changePasswordViewModal.password).map(s -> FormUtils.checkEmail(s) ? null : "Invalid Email")
                .subscribe(s -> binding.email.setError(s), throwable -> throwable.printStackTrace());
        RxUtils.toObservable(changePasswordViewModal.cpassword).map(s -> FormUtils.checkPassword(s) ? null : "Invalid Password")
                .subscribe(s -> binding.password.setError(s), throwable -> throwable.printStackTrace());

        Observable.combineLatest(RxUtils.toObservable(changePasswordViewModal.password), RxUtils.toObservable(changePasswordViewModal.cpassword), (email, password) -> StringUtils.isNotNullOrEmpty(email) && StringUtils.isNotNullOrEmpty(password))
                .subscribe(result -> {
                    binding.loginBtn.setEnabled(result);
                    changePasswordViewModal.enable.set(result);
                    if (!result) {
                    }
                }, Throwable::printStackTrace);
    }

    @Override
    public void callActivity() {

    }

    @Override
    public void callActivity(int k) {
        if (k == 1){
            ActivityCompat.finishAffinity(ChangePasswordActivity.this);
            Intent i = new Intent(ChangePasswordActivity.this,LoginActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(ChangePasswordActivity.this,"Password and confirm password doesn't match",Toast.LENGTH_SHORT).show();
        }
    }
}
