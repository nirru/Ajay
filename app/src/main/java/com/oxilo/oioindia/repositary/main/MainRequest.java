package com.oxilo.oioindia.repositary.main;

/**
 * Created by ManuelVivo on 03/10/15.
 */
public class MainRequest {

    private String cityId;
    private String userId;
    private String position;
    private String categoryId;


    public MainRequest(String cityId) {
        this.cityId = cityId;
    }
    public MainRequest(String userId,String position) {
        this.userId = userId;
        this.position = position;
    }

    public MainRequest(String cat_id,String city_id,String user_id) {
        this.userId = userId;
        this.position = position;
    }


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCityId() {
        return cityId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPosition() {
        return position;
    }
}
