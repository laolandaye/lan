package com.lan.javase._11反射.反射注解应用;

import lombok.Data;

import java.util.Date;

@Data
public class Ather {

    public String id;

    @JsonField(field = "ly")
    private String ly;

    @JsonField(field = "create_time", type="datetime", value = "NOW()")
    public Date create_time;

    @JsonField(field = "update_time", type="datetime", value = "NOW()")
    public Date update_time;

    @JsonField(field = "islr", type="int", value = "0")
    public String islr;

    public String sy;

}
