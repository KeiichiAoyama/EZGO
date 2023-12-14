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
        }else if(obj instanceof ticket){
            return ((ticket) obj).tcName;
        }else if(obj instanceof hotel){
            return ((hotel) obj).hName;
        }else if(obj instanceof tour){
            return ((tour) obj).tpName;
        }else{
            return null;
        }
    }

    public String getImage() {
        if (obj instanceof location) {
            return ((location) obj).lImage;
        }else if(obj instanceof ticket){
            return ((ticket) obj).tcImage;
        }else if(obj instanceof hotel){
            return ((hotel) obj).hImage;
        }else if(obj instanceof tour){
            return ((tour) obj).tpImage;
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
