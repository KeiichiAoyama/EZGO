package com.example.project;

public class ExploreData {
    private location obj;

    public ExploreData(location obj) {
        this.obj = obj;
    }

    public String getTitle() {
        return obj.lName;
    }

    public String getImage() {
        return obj.lImage;
    }

    public int getRating() {
        return obj.lLikes;
    }

    public location getObj() {
        return this.obj;
    }
}
