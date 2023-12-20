package com.example.project;

public class SearchItem<T> {
    private T obj;

    public SearchItem(T obj) {
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

    public String getType() {
        if (obj instanceof location) {
            return "Destination";
        }else if(obj instanceof ticket){
            return "Ticket";
        }else if(obj instanceof hotel){
            return "Hotel";
        }else if(obj instanceof tour){
            return "Tour Package";
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
