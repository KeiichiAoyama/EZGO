package com.example.project;

import java.io.Serializable;
import java.time.LocalDate;
import com.google.gson.Gson;

public class User {
    public String id;
    public String name;
    public String address;
    public String phone;
    public String email;
    public LocalDate bday;
    public String pfp;

    public User(){}

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static User fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, User.class);
    }
}
