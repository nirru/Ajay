package com.oxilo.oioindia.viewmodal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.oxilo.oioindia.modal.Business;
import com.oxilo.oioindia.modal.Details;
import com.oxilo.oioindia.repositary.main.MainRequestManager;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by nikk on 23/10/17.
 */

public class BusinesDetailViewModal extends AndroidViewModel {
    Application application;
    public ObservableBoolean enable = new ObservableBoolean();
    public BusinesDetailViewModal(Application application) {
        super(application);
        this.application = application;
        enable.set(true);
    }
    public Observable<Response<ResponseBody>> getBusinessDetail(String product_id){
        return MainRequestManager.getInstance(application.getApplicationContext()).getBusinessDetails(product_id);
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

        public Factory( Application application) {
            mApplication = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new BusinesDetailViewModal(mApplication);
        }
    }
}
