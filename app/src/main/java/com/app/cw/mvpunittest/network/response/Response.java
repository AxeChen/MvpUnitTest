package com.app.cw.mvpunittest.network.response;

/**
 * Created by Zaifeng on 2018/2/28.
 * 返回结果封装
 */

public class Response<T> {

    private int errorCode; // 返回的code
    private T data; // 具体的数据结果
    private String errorMsg; // message 可用来返回接口的说明

    public Response(int errorCode, T data, String errorMsg) {
        this.errorCode = errorCode;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return errorCode;
    }

    public void setCode(int code) {
        this.errorCode = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return errorMsg;
    }

    public void setMsg(String msg) {
        this.errorMsg = msg;
    }
}
