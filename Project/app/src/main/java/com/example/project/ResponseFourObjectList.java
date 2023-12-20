package com.example.project;

import java.util.List;

public class ResponseFourObjectList<T,U,V,W> {
    private boolean Success;
    private List<T> Data1;
    private List<U> Data2;
    private List<V> Data3;
    private List<W> Data4;

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

    public List<W> getData4() {
        return Data4;
    }
}
