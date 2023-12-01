package com.example.project;

public class HotelData {

    private String Id;
    private String Name;
    private String Address;
    private String RoomType;
    private String Price;
    private String Date;


    public HotelData(String id, String name, String address, String roomType, String price, String date) {
        Id = id;
        Name = name;
        Address = address;
        RoomType = roomType;
        Price = price;
        Date = date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
