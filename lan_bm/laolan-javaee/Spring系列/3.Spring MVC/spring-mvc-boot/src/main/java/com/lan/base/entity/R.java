package com.lan.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回对象
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> extends BaseEntity {

    public final static int SuccessCode = 1000;
    public final static int FailCode = 1001;

    private int code;
    private String msg;
    private T data;

    public static R success(){
        return new R(SuccessCode,"操作成功",null);
    }

    public static <T> R success(String msg){
        return new R(SuccessCode,msg,null);
    }

    public static <T> R success(T data){
        return new R(SuccessCode,"success",data);
    }

    public static <T> R success(String msg,T data){
        return new R(SuccessCode,msg,data);
    }

    public static R fail(){
        return new R(FailCode,"fail",null);
    }

    public static <T> R fail(String msg){
        return new R(FailCode,msg,null);
    }

    public static <T> R fail(T data){
        return new R(FailCode,"fail",data);
    }

    public static <T> R fail(String msg,T data){
        return new R(FailCode,msg,data);
    }

}
