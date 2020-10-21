package com.temp.server.data.http.resp;

public class BaseResp<T> {
    private int code = -999;
    private String msg = "not initialized";
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BaseResp<T> build(int code, String msg) {
        BaseResp<T> resp = new BaseResp<>();
        resp.setCode(code);
        resp.setMsg(msg == null ? "" : msg);
        return resp;
    }
}
