package com.oxilo.oioindia.modal;

/**
 * Created by nikk on 28/11/17.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "result1",
        "result2"
})
public class Details implements Parcelable
{

    @JsonProperty("message")
    private String message;
    @JsonProperty("result1")
    private List<BusinessDetails> businessDetails = null;
    @JsonProperty("result2")
    private List<BusinessImage> businessImage = null;
    public final static Parcelable.Creator<Details> CREATOR = new Creator<Details>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Details createFromParcel(Parcel in) {
            return new Details(in);
        }

        public Details[] newArray(int size) {
            return (new Details[size]);
        }

    }
            ;

    protected Details(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.businessDetails, (BusinessDetails.class.getClassLoader()));
        in.readList(this.businessImage, (BusinessImage.class.getClassLoader()));
    }

    public Details() {
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("result1")
    public List<BusinessDetails> getBusinessDetails() {
        return businessDetails;
    }

    @JsonProperty("result1")
    public void setBusinessDetails(List<BusinessDetails> result1) {
        this.businessDetails = result1;
    }

    @JsonProperty("result2")
    public List<BusinessImage> getResult2() {
        return businessImage;
    }

    @JsonProperty("result2")
    public void setResult2(List<BusinessImage> result2) {
        this.businessImage = result2;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeList(businessDetails);
        dest.writeList(businessImage);
    }

    public int describeContents() {
        return 0;
    }

}
