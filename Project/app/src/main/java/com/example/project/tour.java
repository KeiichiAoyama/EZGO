package com.example.project;

import com.google.gson.Gson;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class tour implements Serializable {
    public String tourID;
    public String productID;
    public int cityID;
    public String tpName;
    public String tpDate;
    public String tpSchedulePlace;
    public String tpMeetingTime;
    public int tpPrice;
    public int tpSlot;
    public String tpImage;
    public String tpVendor;
    public double tpRating;

    public tour(){}

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static location fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, location.class);
    }
}
