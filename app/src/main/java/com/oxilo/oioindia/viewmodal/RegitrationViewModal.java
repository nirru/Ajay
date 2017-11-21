package com.oxilo.oioindia.viewmodal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.oxilo.oioindia.repositary.register.RegisterRequestManager;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;


/**
 * Created by nikk on 13/10/17.
 */

public class RegitrationViewModal extends AndroidViewModel {


    Application application;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> mobile = new ObservableField<>();
    public ObservableBoolean enable = new ObservableBoolean();
    private CallAnotherActivityNavigator navigator;

    public RegitrationViewModal(Application application,  CallAnotherActivityNavigator navigator) {
        super(application);
        this.application = application;
        this.navigator = navigator;
    }

    public void onRegister(){
        Log.e("GHHH","" + enable.get());
        navigator.callActivity(3);
        RegisterRequestManager.getInstance(application.getApplicationContext()).register(name.get(),name.get(),email.get(),password.get(),mobile.get())
        .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
              //launch main landing screen
                navigator.callActivity(1);
            }

            @Override
            public void onError(Throwable e) {
              e.printStackTrace();
                navigator.callActivity(2);
            }
        });
    }



    public void launchActivity(){
        navigator.callActivity();
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

        public Factory( Application application,CallAnotherActivityNavigator navigatorn) {
            mApplication = application;
            this.navigator = navigatorn;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new RegitrationViewModal(mApplication,navigator);
        }
    }
}
