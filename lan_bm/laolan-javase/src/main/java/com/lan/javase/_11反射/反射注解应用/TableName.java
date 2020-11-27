package com.lan.javase._11反射.反射注解应用;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName {
    /**
     * 数据表名
     * @return
     */
    String value() default "";

}