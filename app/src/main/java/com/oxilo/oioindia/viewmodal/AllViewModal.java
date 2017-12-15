package com.oxilo.oioindia.viewmodal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.oxilo.oioindia.repositary.main.MainRequestManager;
import com.oxilo.oioindia.vo.CategoryResponse;
import com.oxilo.oioindia.vo.Slider;

import io.reactivex.Observable;

/**
 * Created by nikk on 23/10/17.
 */

public class AllViewModal extends AndroidViewModel {
    Application application;
    public AllViewModal(Application application) {
        super(application);
        this.application = application;
    }


    public Observable<CategoryResponse> getCategory(String pos){
        return MainRequestManager.getInstance(application.getApplicationContext()).getCategory("ic_name",pos);
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
            return (T) new AllViewModal(mApplication);
        }
    }
}
