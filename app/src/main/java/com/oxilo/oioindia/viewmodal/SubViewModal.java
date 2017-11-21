package com.oxilo.oioindia.viewmodal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.oxilo.oioindia.repositary.main.MainRequestManager;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.vo.CategoryResponse;
import com.oxilo.oioindia.vo.SubCategoryResponse;

import io.reactivex.Observable;

/**
 * Created by nikk on 23/10/17.
 */

public class SubViewModal extends AndroidViewModel {
    Application application;
    public ObservableBoolean enable = new ObservableBoolean();
    public SubViewModal(Application application) {
        super(application);
        this.application = application;
        enable.set(true);
    }




    public Observable<SubCategoryResponse> getSubCategory(String serviceid){
        return MainRequestManager.getInstance(application.getApplicationContext()).getSubCategory("735");
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
            return (T) new SubViewModal(mApplication);
        }
    }
}
