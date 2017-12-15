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
        "productID",
        "name",
        "address1",
        "address2",
        "description",
        "email",
        "image",
        "phonenumber1",
        "phonenumber2",
        "contactperson",
        "area",
        "pincode",
        "landlinenumber",
        "landmark",
        "website",
        "facebook",
        "google",
        "twitter",
        "d11",
        "d12",
        "d21",
        "d22",
        "d31",
        "d32",
        "d41",
        "d42",
        "d51",
        "d52",
        "d61",
        "d62",
        "d71",
        "d72"
})
public class BusinessDetails implements Parcelable
{

    @JsonProperty("productID")
    private String productID;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address1")
    private String address1;
    @JsonProperty("address2")
    private String address2;
    @JsonProperty("description")
    private String description;
    @JsonProperty("email")
    private String email;
    @JsonProperty("image")
    private String image;
    @JsonProperty("phonenumber1")
    private String phonenumber1;
    @JsonProperty("phonenumber2")
    private String phonenumber2;
    @JsonProperty("contactperson")
    private String contactperson;
    @JsonProperty("area")
    private String area;
    @JsonProperty("pincode")
    private String pincode;
    @JsonProperty("landlinenumber")
    private String landlinenumber;
    @JsonProperty("landmark")
    private String landmark;
    @JsonProperty("website")
    private String website;
    @JsonProperty("facebook")
    private String facebook;
    @JsonProperty("google")
    private String google;
    @JsonProperty("twitter")
    private String twitter;
    @JsonProperty("d11")
    private String d11;
    @JsonProperty("d12")
    private String d12;
    @JsonProperty("d21")
    private String d21;
    @JsonProperty("d22")
    private String d22;
    @JsonProperty("d31")
    private String d31;
    @JsonProperty("d32")
    private String d32;
    @JsonProperty("d41")
    private String d41;
    @JsonProperty("d42")
    private String d42;
    @JsonProperty("d51")
    private String d51;
    @JsonProperty("d52")
    private String d52;
    @JsonProperty("d61")
    private String d61;
    @JsonProperty("d62")
    private String d62;
    @JsonProperty("d71")
    private String d71;
    @JsonProperty("d72")
    private String d72;
    public final static Parcelable.Creator<BusinessDetails> CREATOR = new Creator<BusinessDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BusinessDetails createFromParcel(Parcel in) {
            return new BusinessDetails(in);
        }

        public BusinessDetails [] newArray(int size) {
            return (new BusinessDetails[size]);
        }

    };

    protected BusinessDetails(Parcel in) {
        this.productID = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.address1 = ((String) in.readValue((String.class.getClassLoader())));
        this.address2 = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.phonenumber1 = ((String) in.readValue((String.class.getClassLoader())));
        this.phonenumber2 = ((String) in.readValue((String.class.getClassLoader())));
        this.contactperson = ((String) in.readValue((String.class.getClassLoader())));
        this.area = ((String) in.readValue((String.class.getClassLoader())));
        this.pincode = ((String) in.readValue((String.class.getClassLoader())));
        this.landlinenumber = ((String) in.readValue((String.class.getClassLoader())));
        this.landmark = ((String) in.readValue((String.class.getClassLoader())));
        this.website = ((String) in.readValue((String.class.getClassLoader())));
        this.facebook = ((String) in.readValue((String.class.getClassLoader())));
        this.google = ((String) in.readValue((String.class.getClassLoader())));
        this.twitter = ((String) in.readValue((String.class.getClassLoader())));
        this.d11 = ((String) in.readValue((String.class.getClassLoader())));
        this.d12 = ((String) in.readValue((String.class.getClassLoader())));
        this.d21 = ((String) in.readValue((String.class.getClassLoader())));
        this.d22 = ((String) in.readValue((String.class.getClassLoader())));
        this.d31 = ((String) in.readValue((String.class.getClassLoader())));
        this.d32 = ((String) in.readValue((String.class.getClassLoader())));
        this.d41 = ((String) in.readValue((String.class.getClassLoader())));
        this.d42 = ((String) in.readValue((String.class.getClassLoader())));
        this.d51 = ((String) in.readValue((String.class.getClassLoader())));
        this.d52 = ((String) in.readValue((String.class.getClassLoader())));
        this.d61 = ((String) in.readValue((String.class.getClassLoader())));
        this.d62 = ((String) in.readValue((String.class.getClassLoader())));
        this.d71 = ((String) in.readValue((String.class.getClassLoader())));
        this.d72 = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BusinessDetails() {
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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
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

    @JsonProperty("contactperson")
    public String getContactperson() {
        return contactperson;
    }

    @JsonProperty("contactperson")
    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    @JsonProperty("area")
    public String getArea() {
        return area;
    }

    @JsonProperty("area")
    public void setArea(String area) {
        this.area = area;
    }

    @JsonProperty("pincode")
    public String getPincode() {
        return pincode;
    }

    @JsonProperty("pincode")
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @JsonProperty("landlinenumber")
    public String getLandlinenumber() {
        return landlinenumber;
    }

    @JsonProperty("landlinenumber")
    public void setLandlinenumber(String landlinenumber) {
        this.landlinenumber = landlinenumber;
    }

    @JsonProperty("landmark")
    public String getLandmark() {
        return landmark;
    }

    @JsonProperty("landmark")
    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("facebook")
    public String getFacebook() {
        return facebook;
    }

    @JsonProperty("facebook")
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    @JsonProperty("google")
    public String getGoogle() {
        return google;
    }

    @JsonProperty("google")
    public void setGoogle(String google) {
        this.google = google;
    }

    @JsonProperty("twitter")
    public String getTwitter() {
        return twitter;
    }

    @JsonProperty("twitter")
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @JsonProperty("d11")
    public String getD11() {
        return d11;
    }

    @JsonProperty("d11")
    public void setD11(String d11) {
        this.d11 = d11;
    }

    @JsonProperty("d12")
    public String getD12() {
        return d12;
    }

    @JsonProperty("d12")
    public void setD12(String d12) {
        this.d12 = d12;
    }

    @JsonProperty("d21")
    public String getD21() {
        return d21;
    }

    @JsonProperty("d21")
    public void setD21(String d21) {
        this.d21 = d21;
    }

    @JsonProperty("d22")
    public String getD22() {
        return d22;
    }

    @JsonProperty("d22")
    public void setD22(String d22) {
        this.d22 = d22;
    }

    @JsonProperty("d31")
    public String getD31() {
        return d31;
    }

    @JsonProperty("d31")
    public void setD31(String d31) {
        this.d31 = d31;
    }

    @JsonProperty("d32")
    public String getD32() {
        return d32;
    }

    @JsonProperty("d32")
    public void setD32(String d32) {
        this.d32 = d32;
    }

    @JsonProperty("d41")
    public String getD41() {
        return d41;
    }

    @JsonProperty("d41")
    public void setD41(String d41) {
        this.d41 = d41;
    }

    @JsonProperty("d42")
    public String getD42() {
        return d42;
    }

    @JsonProperty("d42")
    public void setD42(String d42) {
        this.d42 = d42;
    }

    @JsonProperty("d51")
    public String getD51() {
        return d51;
    }

    @JsonProperty("d51")
    public void setD51(String d51) {
        this.d51 = d51;
    }

    @JsonProperty("d52")
    public String getD52() {
        return d52;
    }

    @JsonProperty("d52")
    public void setD52(String d52) {
        this.d52 = d52;
    }

    @JsonProperty("d61")
    public String getD61() {
        return d61;
    }

    @JsonProperty("d61")
    public void setD61(String d61) {
        this.d61 = d61;
    }

    @JsonProperty("d62")
    public String getD62() {
        return d62;
    }

    @JsonProperty("d62")
    public void setD62(String d62) {
        this.d62 = d62;
    }

    @JsonProperty("d71")
    public String getD71() {
        return d71;
    }

    @JsonProperty("d71")
    public void setD71(String d71) {
        this.d71 = d71;
    }

    @JsonProperty("d72")
    public String getD72() {
        return d72;
    }

    @JsonProperty("d72")
    public void setD72(String d72) {
        this.d72 = d72;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productID);
        dest.writeValue(name);
        dest.writeValue(address1);
        dest.writeValue(address2);
        dest.writeValue(description);
        dest.writeValue(email);
        dest.writeValue(image);
        dest.writeValue(phonenumber1);
        dest.writeValue(phonenumber2);
        dest.writeValue(contactperson);
        dest.writeValue(area);
        dest.writeValue(pincode);
        dest.writeValue(landlinenumber);
        dest.writeValue(landmark);
        dest.writeValue(website);
        dest.writeValue(facebook);
        dest.writeValue(google);
        dest.writeValue(twitter);
        dest.writeValue(d11);
        dest.writeValue(d12);
        dest.writeValue(d21);
        dest.writeValue(d22);
        dest.writeValue(d31);
        dest.writeValue(d32);
        dest.writeValue(d41);
        dest.writeValue(d42);
        dest.writeValue(d51);
        dest.writeValue(d52);
        dest.writeValue(d61);
        dest.writeValue(d62);
        dest.writeValue(d71);
        dest.writeValue(d72);
    }

    public int describeContents() {
        return 0;
    }

}


