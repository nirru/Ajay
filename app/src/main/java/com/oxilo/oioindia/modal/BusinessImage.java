package com.oxilo.oioindia.modal;

/**
 * Created by nikk on 28/11/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "img_name"
})
public class BusinessImage implements Parcelable
{

    @JsonProperty("img_name")
    private String imgName;
    public final static Parcelable.Creator<BusinessImage> CREATOR = new Creator<BusinessImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BusinessImage createFromParcel(Parcel in) {
            return new BusinessImage(in);
        }

        public BusinessImage [] newArray(int size) {
            return (new BusinessImage[size]);
        }

    }
            ;

    protected BusinessImage(Parcel in) {
        this.imgName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BusinessImage() {
    }

    @JsonProperty("img_name")
    public String getImgName() {
        return imgName;
    }

    @JsonProperty("img_name")
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(imgName);
    }

    public int describeContents() {
        return 0;
    }

}
