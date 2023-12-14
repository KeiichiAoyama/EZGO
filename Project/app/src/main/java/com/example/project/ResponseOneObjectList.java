package com.example.project;

import java.util.List;

public class ResponseOneObjectList<T> {
    private boolean Success;
    private List<T> Data;

    public boolean isSuccess() {
        return Success;
    }

    public List<T> getData() {
        return Data;
    }
}
