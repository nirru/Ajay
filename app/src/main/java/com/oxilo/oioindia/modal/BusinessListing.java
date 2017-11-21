package com.oxilo.oioindia.modal;

/**
 * Created by nikk on 11/11/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productID",
        "name",
        "contactperson",
        "image",
        "phonenumber1",
        "phonenumber2",
        "address1",
        "address2"
})
public class BusinessListing implements Parcelable
{

    @JsonProperty("productID")
    private String productID;
    @JsonProperty("name")
    private String name;
    @JsonProperty("contactperson")
    private String contactperson;
    @JsonProperty("image")
    private String image;
    @JsonProperty("phonenumber1")
    private String phonenumber1;
    @JsonProperty("phonenumber2")
    private String phonenumber2;
    @JsonProperty("address1")
    private String address1;
    @JsonProperty("address2")
    private String address2;
    public final static Parcelable.Creator<BusinessListing> CREATOR = new Creator<BusinessListing>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BusinessListing createFromParcel(Parcel in) {
            return new BusinessListing(in);
        }

        public BusinessListing[] newArray(int size) {
            return (new BusinessListing[size]);
        }

    }
            ;

    protected BusinessListing(Parcel in) {
        this.productID = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.contactperson = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.phonenumber1 = ((String) in.readValue((String.class.getClassLoader())));
        this.phonenumber2 = ((String) in.readValue((String.class.getClassLoader())));
        this.address1 = ((String) in.readValue((String.class.getClassLoader())));
        this.address2 = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BusinessListing() {
    }

    @JsonProperty("productID")
    public String getProductID() {
        return productID;
    }

    @JsonProperty("productID")
    public void setProductID(String productID) {
        this.productID = productID;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("contactperson")
    public String getContactperson() {
        return contactperson;
    }

    @JsonProperty("contactperson")
    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("phonenumber1")
    public String getPhonenumber1() {
        return phonenumber1;
    }

    @JsonProperty("phonenumber1")
    public void setPhonenumber1(String phonenumber1) {
        this.phonenumber1 = phonenumber1;
    }

    @JsonProperty("phonenumber2")
    public String getPhonenumber2() {
        return phonenumber2;
    }

    @JsonProperty("phonenumber2")
    public void setPhonenumber2(String phonenumber2) {
        this.phonenumber2 = phonenumber2;
    }

    @JsonProperty("address1")
    public String getAddress1() {
        return address1;
    }

    @JsonProperty("address1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("address2")
    public String getAddress2() {
        return address2;
    }

    @JsonProperty("address2")
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productID);
        dest.writeValue(name);
        dest.writeValue(contactperson);
        dest.writeValue(image);
        dest.writeValue(phonenumber1);
        dest.writeValue(phonenumber2);
        dest.writeValue(address1);
        dest.writeValue(address2);
    }

    public int describeContents() {
        return 0;
    }

}
