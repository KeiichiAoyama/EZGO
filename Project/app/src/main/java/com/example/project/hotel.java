package com.example.project;

import com.google.gson.Gson;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class hotel implements Serializable {
    public String hotelID;
    public String productID;
    public int cityID;
    public String hName;
    public String hAddress;
    public String hRoomType;
    public int hPrice;
    public int hNights;
    public String hImage;
    public String hVendor;
    public double hRating;
    public String hDate;

    public hotel(){}

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static location fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, location.class);
    }
}
