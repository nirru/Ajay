package com.oxilo.oioindia.modal;

/**
 * Created by nikk on 5/11/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cityid",
        "cityname"
})
public class City implements Parcelable
{

    @JsonProperty("cityid")
    private String cityid;
    @JsonProperty("cityname")
    private String cityname;
    private boolean isChecked;


    public final static Parcelable.Creator<City> CREATOR = new Creator<City>() {


        @SuppressWarnings({
                "unchecked"
        })
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        public City[] newArray(int size) {
            return (new City[size]);
        }

    }
            ;

    protected City(Parcel in) {
        this.cityid = ((String) in.readValue((String.class.getClassLoader())));
        this.cityname = ((String) in.readValue((String.class.getClassLoader())));
        this.isChecked = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public City() {
    }

    public City(String name){

        this.cityname = name;
    }

    @JsonProperty("cityid")
    public String getCityid() {
        return cityid;
    }

    @JsonProperty("cityid")
    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    @JsonProperty("cityname")
    public String getCityname() {
        return cityname;
    }

    @JsonProperty("cityname")
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cityid);
        dest.writeValue(cityname);
        dest.writeValue(isChecked);
    }

    public int describeContents() {
        return 0;
    }

}
