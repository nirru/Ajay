package com.oxilo.oioindia.vo;

/**
 * Created by nikk on 29/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "subcatID",
        "subcatname"
})
public class SubCategory implements Parcelable
{

    @JsonProperty("subcatID")
    private String subcatID;
    @JsonProperty("subcatname")
    private String subcatname;
    public final static Parcelable.Creator<SubCategory> CREATOR = new Creator<SubCategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SubCategory createFromParcel(Parcel in) {
            return new SubCategory(in);
        }

        public SubCategory[] newArray(int size) {
            return (new SubCategory[size]);
        }

    }
            ;

    protected SubCategory(Parcel in) {
        this.subcatID = ((String) in.readValue((String.class.getClassLoader())));
        this.subcatname = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SubCategory() {
    }

    @JsonProperty("subcatID")
    public String getSubcatID() {
        return subcatID;
    }

    @JsonProperty("subcatID")
    public void setSubcatID(String subcatID) {
        this.subcatID = subcatID;
    }

    @JsonProperty("subcatname")
    public String getSubcatname() {
        return subcatname;
    }

    @JsonProperty("subcatname")
    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(subcatID);
        dest.writeValue(subcatname);
    }

    public int describeContents() {
        return 0;
    }

}
