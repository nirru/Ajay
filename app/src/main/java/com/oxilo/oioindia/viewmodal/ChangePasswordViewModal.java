package com.oxilo.oioindia.viewmodal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.oxilo.oioindia.repositary.forgotpassword.ForgetRequestManager;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;

import org.json.JSONObject;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by nikk on 23/10/17.
 */

public class ChangePasswordViewModal extends AndroidViewModel {
    Application application;
    CallAnotherActivityNavigator navigator;
    public String user_id;
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> cpassword = new ObservableField<>();
    public ObservableBoolean enable = new ObservableBoolean();
    public ChangePasswordViewModal(Application application, CallAnotherActivityNavigator navigator,String user_id) {
        super(application);
        this.application = application;
        this.navigator = navigator;
        this.user_id = user_id;
        enable.set(false);
    }

    public void resetPwd(){
         ForgetRequestManager.getInstance(application.getApplicationContext()).resetPassword(user_id,password.get(),cpassword.get())
                .subscribe(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> responseBodyResponse) throws Exception {
                        try
                        {
                            String sd = new String(responseBodyResponse.body().bytes());
                            JSONObject mapping = new JSONObject(sd);
                            navigator.callActivity(ic_name);
                            if (mapping.getString("result").equals("ic_name")){
                                navigator.callActivity(ic_name);
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
                    }
                });
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
        String user_id;

        public Factory( Application application,CallAnotherActivityNavigator navigator,String user_id) {
            mApplication = application;
            this.navigator = navigator;
            this.user_id = user_id;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ChangePasswordViewModal(mApplication,navigator,user_id);
        }
    }
}
