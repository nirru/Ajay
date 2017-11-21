package com.oxilo.oioindia.repositary.main;

import com.oxilo.oioindia.modal.Business;
import com.oxilo.oioindia.modal.CityResponse;
import com.oxilo.oioindia.vo.CategoryResponse;
import com.oxilo.oioindia.vo.Slider;
import com.oxilo.oioindia.vo.SubCategoryResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nikk on 2/10/17.
 */
public interface IMainAPI {
    @FormUrlEncoded
    @POST("api/get-sliders.php")
    Observable<Slider> image(
            @Field("city_id") String cityID);


    @FormUrlEncoded
    @POST("api/get-categories.php")
    Observable<CategoryResponse> category(
            @Field("user_id") String userid,
            @Field("position") String pos);

    @FormUrlEncoded
    @POST("api/get-sub-categories.php")
    Observable<SubCategoryResponse> subcategory(
            @Field("catID") String parentID);

    @FormUrlEncoded
    @POST("api/get-businesses.php")
    Observable<Business> business(
            @Field("cat_id") String cat_id,
            @Field("city_id") String city_id);


    @POST("api/get-cities.php")
    Observable<CityResponse> city();


}
