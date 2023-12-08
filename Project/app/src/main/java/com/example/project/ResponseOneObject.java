package com.example.project;

public class ResponseOneObject<T> {
    private boolean Success;
    private T Data;

    public boolean isSuccess() {
        return Success;
    }

    public T getData() {
        return Data;
    }
}
