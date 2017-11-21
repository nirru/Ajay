package com.oxilo.oioindia.viewmodal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.oxilo.oioindia.repositary.forgotpassword.ForgetRequestManager;
import com.oxilo.oioindia.repositary.main.MainRequestManager;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.view.activity.ChangePasswordActivity;
import com.oxilo.oioindia.view.activity.ForgetActivity;
import com.oxilo.oioindia.vo.SubCategoryResponse;

import org.json.JSONObject;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by nikk on 23/10/17.
 */

public class ForgetViewModal extends AndroidViewModel {
    Application application;
    CallAnotherActivityNavigator navigator;
    public ObservableField<String> mobile = new ObservableField<>();
    public ObservableField<String> otp = new ObservableField<>();
    public ObservableField<String> user_id = new ObservableField<>();
    public ObservableBoolean enable = new ObservableBoolean();
    public ObservableBoolean txt_enable = new ObservableBoolean();
    public ObservableBoolean txt_enable_2 = new ObservableBoolean();
    public ObservableBoolean enable_otp_1 = new ObservableBoolean();
    public ObservableBoolean enable_otp_2 = new ObservableBoolean();
    public ForgetViewModal(Application application,CallAnotherActivityNavigator navigator) {
        super(application);
        this.application = application;
        this.navigator = navigator;
        enable.set(false);

        txt_enable.set(true);
        txt_enable_2.set(false);

        enable_otp_1.set(false);
        enable_otp_2.set(false);
    }

    public void forgetPwd(){
         ForgetRequestManager.getInstance(application.getApplicationContext()).forget(mobile.get())
                .subscribe(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                        try
                        {
                            String sd = new String(responseBodyResponse.body().bytes());
                            JSONObject mapping = new JSONObject(sd);
                            txt_enable_2.set(true);
                            txt_enable.set(false);

                            if (mapping.getString("result").equals("1")){
                                navigator.callActivity(1);
                                user_id.set(mapping.getString("userID"));
                            }else{
                                navigator.callActivity(4);
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
                    }
                });
    }



    public void otpVerfication(){
        navigator.callActivity(3);

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
            return (T) new ForgetViewModal(mApplication,navigator);
        }
    }
}
