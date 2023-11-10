package com.example.project;

public class ExploreData {

    private int gambar;
    private String place;
    private int rating;
    private boolean like;

    public ExploreData(int gambar, String place, int rating, boolean like) {
        this.gambar = gambar;
        this.place = place;
        this.rating = rating;
        this.like = like;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
