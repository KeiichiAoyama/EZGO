package com.example.project;

public class TicketData {
    private String Id;
    private String Date;
    private String From;
    private String Dest;
    private String DeptTime;
    private String TravelTime;
    private int Price;
    private String Type;
    private String ArivTime;
    private String TicketType;

    public TicketData(String id, String date, String from, String dest, String deptTime, String travelTime, int price, String type, String arivTime, String ticketType) {
        Id = id;
        Date = date;
        From = from;
        Dest = dest;
        DeptTime = deptTime;
        TravelTime = travelTime;
        Price = price;
        Type = type;
        ArivTime = arivTime;
        TicketType = ticketType;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getDest() {
        return Dest;
    }

    public void setDest(String dest) {
        Dest = dest;
    }

    public String getDeptTime() {
        return DeptTime;
    }

    public void setDeptTime(String deptTime) {
        DeptTime = deptTime;
    }

    public String getTravelTime() {
        return TravelTime;
    }

    public void setTravelTime(String travelTime) {
        TravelTime = travelTime;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getArivTime() {
        return ArivTime;
    }

    public void setArivTime(String arivTime) {
        ArivTime = arivTime;
    }

    public String getTicketType() {
        return TicketType;
    }

    public void setTicketType(String ticketType) {
        TicketType = ticketType;
    }
}
