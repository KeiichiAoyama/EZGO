package com.example.project;

import com.google.gson.Gson;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class ticket implements Serializable {
    public String ticketID;
    public String productID;
    public String tcType;
    public String tcName;
    public String tcDate;
    public String tcDepartureTime;
    public int tcFrom;
    public int tcDestination;
    public String tcTravelTime;
    public int tcPrice;
    public int tcSeat;
    public String tcImage;
    public String tcVendor;
    public double tcRating;

    public ticket(){}

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static location fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, location.class);
    }
}
