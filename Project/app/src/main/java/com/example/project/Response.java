package com.example.project;

import java.util.List;

public class Response<T, U, V, W, X>  {
    private boolean success;
    private List<T> dataList1;
    private List<U> dataList2;
    private List<V> dataList3;
    private List<W> dataList4;
    private List<X> dataList5;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getDataList1() {
        return dataList1;
    }

    public void setDataList1(List<T> dataList1) {
        this.dataList1 = dataList1;
    }

    public List<U> getDataList2() {
        return dataList2;
    }

    public void setDataList2(List<U> dataList2) {
        this.dataList2 = dataList2;
    }

    public List<V> getDataList3() {
        return dataList3;
    }

    public void setDataList3(List<V> dataList3) {
        this.dataList3 = dataList3;
    }

    public List<W> getDataList4() {
        return dataList4;
    }

    public void setDataList4(List<W> dataList4) {
        this.dataList4 = dataList4;
    }

    public List<X> getDataList5() {
        return dataList5;
    }

    public void setDataList5(List<X> dataList5) {
        this.dataList5 = dataList5;
    }
}
