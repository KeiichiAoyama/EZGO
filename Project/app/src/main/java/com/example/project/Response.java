package com.example.project;

import java.util.List;

import java.util.List;
import java.util.Map;

public class Response {
    private boolean success;
    private Map<String, List<?>> dataLists;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, List<?>> getDataLists() {
        return dataLists;
    }

    public void setDataLists(Map<String, List<?>> dataLists) {
        this.dataLists = dataLists;
    }
}

