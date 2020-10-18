package com.temp.server.data.http.req;

public class BaseReq<T> {
    private int id;
    private T data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
