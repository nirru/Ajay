package com.oxilo.oioindia.modal;

/**
 * Created by nikk on 11/11/17.
 */

import java.util.ArrayList;
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
        "result"
})
public class Business implements Parcelable
{

    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private List<BusinessListing> result = new ArrayList<>();
    public final static Parcelable.Creator<Business> CREATOR = new Creator<Business>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Business createFromParcel(Parcel in) {
            return new Business(in);
        }

        public Business[] newArray(int size) {
            return (new Business[size]);
        }

    }
            ;

    protected Business(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.result, (BusinessListing.class.getClassLoader()));
    }

    public Business() {
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("result")
    public List<BusinessListing> getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(List<BusinessListing> result) {
        this.result = result;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeList(result);
    }

    public int describeContents() {
        return 0;
    }

}
