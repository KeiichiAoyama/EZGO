package com.example.project;

import java.io.Serializable;

public class MyItem<T>{
    private T obj;

    public MyItem(T obj) {
        this.obj = obj;
    }

    public String getTitle() {
        if (obj instanceof location) {
            return ((location) obj).lName;
        }else{
            return null;
        }
    }

    public String getImage() {
        if (obj instanceof location) {
            return ((location) obj).lImage;
        }else{
            return null;
        }
    }

    public Class<?> getObjectType() {
        return obj.getClass();
    }

    public T getObj(){
        return obj;
    }
}
