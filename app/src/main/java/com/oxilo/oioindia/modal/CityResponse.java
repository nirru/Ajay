package com.oxilo.oioindia.modal;

/**
 * Created by nikk on 5/11/17.
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
public class CityResponse implements Parcelable
{

    @JsonProperty("message")
    private String message;
    @JsonProperty("result")
    private ArrayList<City> result = null;
    public final static Parcelable.Creator<CityResponse> CREATOR = new Creator<CityResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CityResponse createFromParcel(Parcel in) {
            return new CityResponse(in);
        }

        public CityResponse[] newArray(int size) {
            return (new CityResponse[size]);
        }

    }
            ;

    protected CityResponse(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.result, (City.class.getClassLoader()));
    }

    public CityResponse() {
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
    public ArrayList<City> getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(ArrayList<City> result) {
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
