package com.example.project;

import com.google.gson.Gson;

import java.io.Serializable;

public class location implements Serializable {
    public int locID;
    public int cityID;
    public String lName;
    public String lDesc;
    public String lImage;
    public int lLikes;

    public location(){}

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static location fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, location.class);
    }
}
