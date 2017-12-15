package com.oxilo.oioindia.repositary.main;

import android.content.Context;

import com.oxilo.oioindia.modal.Business;
import com.oxilo.oioindia.modal.CityResponse;
import com.oxilo.oioindia.modal.Details;
import com.oxilo.oioindia.repositary.main.exception.ImageInternalException;
import com.oxilo.oioindia.retrofit.RetrofitFactory;
import com.oxilo.oioindia.vo.CategoryResponse;
import com.oxilo.oioindia.vo.Slider;
import com.oxilo.oioindia.vo.SubCategoryResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nikk on 2/10/17.
 */
public class MainRequestManager {

    private static MainRequestManager instance;


    private MainAPIService mainAPIService;

    private MainRequestManager(Context context) {
        Retrofit retrofit = null;
        try {
            retrofit = RetrofitFactory.getAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.mainAPIService = new MainAPIService(retrofit);
    }

    public static MainRequestManager getInstance(Context context) {

        synchronized (MainRequestManager.class) {
            if (instance == null) {
                instance = new MainRequestManager(context);
            }

            return instance;
        }
    }



    public Observable<Slider> getSliderImage(String cityId) {
        return mainAPIService.slider(createSliderRequest(cityId));
    }

    public Observable<CategoryResponse> getCategory(String user_id, String pos) {
        return mainAPIService.category(createCategoryRequest(user_id,pos));
    }

    public Observable<SubCategoryResponse> getSubCategory(String serviceId) {
        return mainAPIService.subcategory(serviceId);
    }

    public Observable<Business> getBusiness(String cat_id,String city_id) {
        return mainAPIService.business(cat_id,city_id);
    }

    public Observable<Response<ResponseBody>> getBusinessDetails(String product_id) {
        return mainAPIService.businessDetails(product_id);
    }

    public Observable<CityResponse> getCity() {
        return mainAPIService.city();
    }
    private MainRequest createSliderRequest(String cityId) {

//        String email = "a@a.com";//responseManager.getEmail();
//        String pwd = "12";//responseManager.getPassword();

        if (cityId == null || cityId.isEmpty() ) {
            throw new ImageInternalException();
        }

        return new MainRequest(cityId);
    }

    private MainRequest createCategoryRequest(String user_id,String pos) {

//        String email = "a@a.com";//responseManager.getEmail();
//        String pwd = "12";//responseManager.getPassword();

        if (user_id == null || user_id.isEmpty() || pos == null || pos.isEmpty()) {
            throw new ImageInternalException();
        }

        return new MainRequest(user_id,pos);
    }

}
