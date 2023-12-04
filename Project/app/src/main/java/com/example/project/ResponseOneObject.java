package com.example.project;

public class ResponseOneObject<T> {
    private boolean success;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }
}

