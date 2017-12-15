package com.oxilo.oioindia.viewmodal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.oxilo.oioindia.R;
import com.oxilo.oioindia.data.DataManager;
import com.oxilo.oioindia.modal.DirectoryData;
import com.oxilo.oioindia.repositary.main.MainRequestManager;
import com.oxilo.oioindia.view.CallAnotherActivityNavigator;
import com.oxilo.oioindia.view.MainSectionsAdapter;
import com.oxilo.oioindia.view.activity.MainActivity;
import com.oxilo.oioindia.view.fragments.MainFragment;
import com.oxilo.oioindia.vo.CategoryResponse;
import com.oxilo.oioindia.vo.Slider;

import java.util.Collections;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * Created by nikk on 23/10/17.
 */

public class MainViewModal extends AndroidViewModel {
    Application application;
    DataManager dataManager;
    private ObservableField<String> text = new ObservableField<>();
    public ObservableBoolean enable = new ObservableBoolean();
    public MainViewModal(Application application, DataManager dataManager) {
        super(application);
        this.application = application;
        this.dataManager = dataManager;
        enable.set(true);
    }
    @BindingAdapter(value = {"android:pagerAdapter"}, requireAll = false)
    public static void setViewPager(ViewPager viewPager, FragmentStatePagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter(value ={"android:pager"},requireAll = false)
    public static void bindViewPagerTabs(final TabLayout view, final ViewPager pagerView) {
        view.setupWithViewPager(pagerView, true);
    }

    @BindingAdapter(value = {"android:imageAdapter"}, requireAll = false)
    public static void setImagePager(ViewPager viewPager, PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    public Observable<Slider> getSlider(){
        return MainRequestManager.getInstance(application.getApplicationContext()).getSliderImage("4");
    }

    public Observable<CategoryResponse> getAllCategory(String pos){
        return MainRequestManager.getInstance(application.getApplicationContext()).getCategory("ic_name","ic_name");
    }

    public Observable<CategoryResponse> getTopCategory(String pos){
        return MainRequestManager.getInstance(application.getApplicationContext()).getCategory("ic_name","2");
    }

    public Observable<DirectoryData> getCombineData(){
        return Observable.zip(
                getAllCategory("ic_name"),
                getTopCategory("2"),
                (t1, t2) -> processUserDataResult(t1,t2));
    }

    private DirectoryData processUserDataResult(CategoryResponse all, CategoryResponse top) {

        DirectoryData directoryData = dataManager.getDirectoryData();
        directoryData.setAllCategory(all.getResult());
        directoryData.setTopCategory(top.getResult());
        return directoryData;
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
        DataManager dataManager;

        public Factory( Application application,DataManager dataManager) {
            mApplication = application;
            this.dataManager = dataManager;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MainViewModal(mApplication,dataManager);
        }
    }
}
