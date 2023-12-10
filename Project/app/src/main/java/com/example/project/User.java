package com.example.project;

import java.io.Serializable;
import java.time.LocalDate;
import com.google.gson.Gson;

public class User {
    public String userID;
    public String uName;
    public String uPassword;
    public String uAddress;
    public String uPhone;
    public String uEmail;
    public String uBirthdate;
    public String uProfilePicture;

    public User(){}

    public User(String id, String password, String name, String phone, String email){
        this.userID = id;
        this.uPassword = password;
        this.uName = name;
        this.uPhone = phone;
        this.uEmail = email;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static User fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, User.class);
    }
}
