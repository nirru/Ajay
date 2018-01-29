package com.oxilo.oioindia;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;


import java.io.IOException;
import java.util.Locale;


public class GeoSearchModel {


    public static String addressByLocation(double latitude,double longitude,Context context) {
        String address = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            if (geocoder.getFromLocation(latitude, longitude, 1) == null || geocoder.getFromLocation(latitude, longitude, 1).size() ==0)
                return address;
            Address geoAddress = geocoder.getFromLocation(latitude, longitude, 1).get(0);
            address = geoAddress.getLocality() != null ? geoAddress.getLocality() : geoAddress.getAdminArea();
            address = address == null ? geoAddress.getCountryName() : address + ", " + geoAddress.getCountryName();
            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return address;
        }
    }

    public static String getCityInfo(double latitude, double longitude,Context context){

        String address = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            if (geocoder.getFromLocation(latitude, longitude, 1) == null || geocoder.getFromLocation(latitude, longitude, 1).size() ==0)
                return address;
            Address geoAddress = geocoder.getFromLocation(latitude, longitude, 1).get(0);
            address = geoAddress.getLocality();
            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return address;
        }

    }

    public static String getAdminArea(double latitude, double longitude,Context context){

        String address = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            if (geocoder.getFromLocation(latitude, longitude, 1) == null || geocoder.getFromLocation(latitude, longitude, 1).size() ==0)
                return address;
            Address geoAddress = geocoder.getFromLocation(latitude, longitude, 1).get(0);
            address = geoAddress.getAdminArea();
            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return address;
        }

    }

    public static String fullAddressByLocation(double latitude, double longitude,Context context) {
        String address = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            if (geocoder.getFromLocation(latitude, longitude, 1) == null || geocoder.getFromLocation(latitude, longitude, 1).size() ==0)
                return address;
            Address geoAddress = geocoder.getFromLocation(latitude, longitude, 1).get(0);
            for (int i = 0; i < geoAddress.getMaxAddressLineIndex(); i++) {
                address += geoAddress.getAddressLine(i) + " ";
            }
            return address;
        } catch (IOException e) {
            e.printStackTrace();
            return address;
        }
    }
}
