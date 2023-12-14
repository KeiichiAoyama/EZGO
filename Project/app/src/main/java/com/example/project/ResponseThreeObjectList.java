package com.example.project;

import java.util.List;

public class ResponseThreeObjectList<T,U,V>{
    private boolean Success;
    private List<T> Data1;
    private List<U> Data2;
    private List<V> Data3;

    public boolean isSuccess() {
        return Success;
    }

    public List<T> getData1() {
        return Data1;
    }

    public List<U> getData2() {
        return Data2;
    }

    public List<V> getData3() {
        return Data3;
    }
}
