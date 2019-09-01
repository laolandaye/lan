package com.lan._2model;

import java.io.Serializable;

public class BaseResultModel implements Serializable {

    private String result = "0000";//0000和9999
    private String msg = "success";//返回的信息，目前是在前端写
    private Object data;

    public BaseResultModel(){
    }

    public BaseResultModel(String result, String msg, Object data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    //常用set返回
    public void setResultMsg(String result, String msg) {
        this.result= result;
        this.msg = msg;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result= result;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg =msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data= data;
    }


}

