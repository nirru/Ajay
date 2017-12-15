package com.oxilo.oioindia.repositary.main;


import com.oxilo.oioindia.data.PrivateSharedPreferencesManager;
import com.oxilo.oioindia.modal.Business;
import com.oxilo.oioindia.modal.CityResponse;
import com.oxilo.oioindia.modal.Details;
import com.oxilo.oioindia.repositary.main.exception.ImageAlreadyExistsException;
import com.oxilo.oioindia.repositary.main.exception.ImageTechFailureException;
import com.oxilo.oioindia.repositary.register.IRegisterAPI;
import com.oxilo.oioindia.repositary.register.RegisterRequest;
import com.oxilo.oioindia.repositary.register.exception.RegistrationNicknameAlreadyExistsException;
import com.oxilo.oioindia.repositary.register.exception.RegistrationTechFailureException;
import com.oxilo.oioindia.vo.CategoryResponse;
import com.oxilo.oioindia.vo.Slider;
import com.oxilo.oioindia.vo.SubCategoryResponse;

import java.net.HttpRetryException;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nikk on 2/10/17.
 */
public class MainAPIService {

    private IMainAPI mainAPI;
    private boolean isRequestingLogin;

    public MainAPIService(Retrofit retrofit) {
        this.mainAPI = retrofit.create(IMainAPI.class);
    }

    public boolean isRequestingWeather() {
        return isRequestingLogin;
    }

    public Observable<Slider> slider (MainRequest request) {
        return mainAPI.image(request.getCityId())
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleSliderError)
                .doOnNext(new Consumer<Slider>() {
                    @Override
                    public void accept(Slider registrationResponse) throws Exception {
                       System.out.println(registrationResponse);
                       Object s = registrationResponse;
                        MainAPIService.this.processRegistrationResponse(request, registrationResponse);

                    }
                });

    }

    public Observable<CategoryResponse> category (MainRequest request) {
        return mainAPI.category(request.getUserId(),request.getPosition())
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleCategoryError)
                .doOnNext(new Consumer<CategoryResponse>() {
                    @Override
                    public void accept(CategoryResponse registrationResponse) throws Exception {
                        System.out.println(registrationResponse);
                        Object s = registrationResponse;

                    }
                });

    }


    public Observable<SubCategoryResponse> subcategory (String serviceId) {
        return mainAPI.subcategory(serviceId)
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleSubCategoryError)
                .doOnNext(new Consumer<SubCategoryResponse>() {
                    @Override
                    public void accept(SubCategoryResponse registrationResponse) throws Exception {
                        System.out.println(registrationResponse);
                        Object s = registrationResponse;

                    }
                });

    }

    public Observable<Business> business (String cat_id,String city_id) {
        return mainAPI.business(cat_id,city_id)
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleBusinessError)
                .doOnNext(new Consumer<Business>() {
                    @Override
                    public void accept(Business business) throws Exception {
                        System.out.println(business);
                        Object s = business;

                    }
                });

    }

    public Observable<Response<ResponseBody>> businessDetails (String product_id) {
        return mainAPI.businessdetails(product_id)
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleBusinessDetailsError)
                .doOnNext(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> business) throws Exception {
                        System.out.println(business);
                        Object s = business;

                    }
                });

    }



    public Observable<CityResponse> city () {
        return mainAPI.city()
                .doOnSubscribe(disposable -> isRequestingLogin = true)
                .doOnTerminate(() -> isRequestingLogin = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(this::handleCityError)
                .doOnNext(new Consumer<CityResponse>() {
                    @Override
                    public void accept(CityResponse business) throws Exception {
                        System.out.println(business);
                        Object s = business;

                    }
                });

    }

    private Observable<Slider> handleSliderError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new ImageAlreadyExistsException();
            } else {
                throw new ImageAlreadyExistsException();
            }

        } else {
            throw new ImageTechFailureException();
        }
    }

    private Observable<CategoryResponse> handleCategoryError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new ImageAlreadyExistsException();
            } else {
                throw new ImageAlreadyExistsException();
            }

        } else {
            throw new ImageTechFailureException();
        }
    }

    private Observable<SubCategoryResponse> handleSubCategoryError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new ImageAlreadyExistsException();
            } else {
                throw new ImageAlreadyExistsException();
            }

        } else {
            throw new ImageTechFailureException();
        }
    }

    private Observable<Business> handleBusinessError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new ImageAlreadyExistsException();
            } else {
                throw new ImageAlreadyExistsException();
            }

        } else {
            throw new ImageTechFailureException();
        }
    }

    private Observable<CityResponse> handleCityError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new ImageAlreadyExistsException();
            } else {
                throw new ImageAlreadyExistsException();
            }

        } else {
            throw new ImageTechFailureException();
        }
    }

    private Observable<Response<ResponseBody>> handleBusinessDetailsError(Throwable throwable) {

        if (throwable instanceof HttpRetryException) {

            int status = ((HttpRetryException) throwable).responseCode();

            if (status == 401) {
                throw new ImageAlreadyExistsException();
            } else {
                throw new ImageAlreadyExistsException();
            }

        } else {
            throw new ImageTechFailureException();
        }
    }


    private void processRegistrationResponse(MainRequest request, Slider registrationResponse ) {

//        privateSharedPreferencesManager.storeUserNickname(registrationRequest.getFname());
    }
}
