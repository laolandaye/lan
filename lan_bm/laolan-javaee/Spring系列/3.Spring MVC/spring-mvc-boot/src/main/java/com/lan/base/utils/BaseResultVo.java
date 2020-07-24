package com.lan.base.utils;

import java.io.Serializable;

/**
 * @author : 老兰
 * 统一返回结果
 */
public class BaseResultVo implements Serializable {

    private String result = "0000";//0000和9999
    private String msg = "success";//返回的信息，目前是在前端写
    private Object data;

    public BaseResultVo(){
    }

    public BaseResultVo(String result, String msg, Object data) {
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

