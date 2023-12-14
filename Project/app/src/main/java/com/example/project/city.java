package com.example.project;

import com.google.gson.Gson;

import java.io.Serializable;

public class city implements Serializable {
    public int cityID;
    public String cName;

    public city(){}

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static location fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, location.class);
    }
}
