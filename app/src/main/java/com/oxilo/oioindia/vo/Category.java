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
        "serviceID",
        "name",
        "image"
})
public class Category implements Parcelable{

    @JsonProperty("serviceID")
    private String serviceID;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image")
    private String image;
    public final static Parcelable.Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    }
            ;

    protected Category(Parcel in) {
        this.serviceID = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Category() {
    }

    @JsonProperty("serviceID")
    public String getServiceID() {
        return serviceID;
    }

    @JsonProperty("serviceID")
    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
        dest.writeValue(serviceID);
        dest.writeValue(name);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}

