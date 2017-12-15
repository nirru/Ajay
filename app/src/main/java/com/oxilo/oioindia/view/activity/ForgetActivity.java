package com.oxilo.oioindia.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.databinding.ActivityForgetBinding;
import com.oxilo.oioindia.repositary.forgotpassword.ForgetRequestManager;
import com.oxilo.oioindia.utils.FormUtils;
import com.oxilo.oioindia.utils.RxUtils;
import com.oxilo.oioindia.utils.StringUtils;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.view.common.BaseActivity;
import com.oxilo.oioindia.viewmodal.ForgetViewModal;
import com.oxilo.oioindia.viewmodal.LoginViewModal;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class ForgetActivity extends BaseActivity implements CallAnotherActivityNavigator{

    ActivityForgetBinding binding;
    ForgetViewModal forgetViewModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget);
        ForgetViewModal.Factory factory = new ForgetViewModal.Factory(getApplication(),this);
        forgetViewModal = ViewModelProviders.of(this,factory).get(ForgetViewModal.class);
        binding.setViewModel(forgetViewModal);

        RxUtils.toObservable(forgetViewModal.mobile).map(s -> FormUtils.checkPhone(s) ? null : "Invalid Phone")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        binding.phone.setError(s);
                        if (s!=null && !s.equals("")){
                            forgetViewModal.enable.set(true);
                        }
                    }
                }, throwable -> throwable.printStackTrace());

        RxUtils.toObservable(forgetViewModal.otp).map(s -> FormUtils.checkPassword(s) ? null : "Invalid OTP")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        binding.otpId.setError(s);
                        if (s!=null && !s.equals("")){
                            forgetViewModal.enable_otp_1.set(true);
                            forgetViewModal.enable_otp_2.set(false);
                        }
                    }
                }, throwable -> throwable.printStackTrace());



    }

    @Override
    public void callActivity() {

    }

    @Override
    public void callActivity(int k) {

        if (k==1){
            binding.forgotBtn.setVisibility(View.GONE);
            forgetViewModal.enable_otp_1.set(true);
            forgetViewModal.txt_enable_2.set(true);
            forgetViewModal.txt_enable.set(false);
        }

        if (k == 4){
            Toast.makeText(ForgetActivity.this,"mobile number not found",Toast.LENGTH_SHORT).show();
        }

        if (k==3){
            if (!binding.otpId.getText().toString().equals("")){
                ForgetRequestManager.getInstance(getApplicationContext()).otpVarification(forgetViewModal.mobile.get(),forgetViewModal.otp.get())
                        .subscribe(new Consumer<Response<ResponseBody>>() {
                            @Override
                            public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                                try{
                                    String sd = new String(responseBodyResponse.body().bytes());
                                    JSONObject mapping = new JSONObject(sd);
                                    if (mapping.getString("result").equals("ic_name")){
                                        Intent i = new Intent(ForgetActivity.this,ChangePasswordActivity.class);

                                        i.putExtra("A",forgetViewModal.user_id.get());
                                        startActivity(i);
                                    }else{

                                    }
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                        },Throwable::printStackTrace);
            }
            else{
                binding.otpId.setError("Please enter otp");
            }
        }
    }
}
