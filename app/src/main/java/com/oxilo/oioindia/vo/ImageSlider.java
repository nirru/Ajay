package com.oxilo.oioindia.vo;

/**
 * Created by nikk on 28/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sliderID",
        "image"
})
public class ImageSlider implements Parcelable
{

    @JsonProperty("sliderID")
    private String sliderID;
    @JsonProperty("image")
    private String image;
    public final static Parcelable.Creator<ImageSlider> CREATOR = new Creator<ImageSlider>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ImageSlider createFromParcel(Parcel in) {
            return new ImageSlider(in);
        }

        public ImageSlider[] newArray(int size) {
            return (new ImageSlider[size]);
        }

    }
            ;

    protected ImageSlider(Parcel in) {
        this.sliderID = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ImageSlider() {
    }

    @JsonProperty("sliderID")
    public String getSliderID() {
        return sliderID;
    }

    @JsonProperty("sliderID")
    public void setSliderID(String sliderID) {
        this.sliderID = sliderID;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sliderID);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}
