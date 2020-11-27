package com.lan.javase._11反射.反射注解应用;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonField {

    /**
     * 对应到table表的字段名
     * @return
     */
    String field();

    /**
     * 对应到table表的字段类型
     * @return
     */
    String type() default "varchar";

    /**
     * 是否必填
     */
    boolean required() default false;

    /**
     * 对应到table表的字段类型, 是否是日期字符串
     * @return
     */
    boolean isDateTime() default false;

    /**
     * 默认值
     * @return
     */
    String value() default "";

    /**
     * 是否需要字典转换
     */
    boolean convert() default false;

    /**
     * 字段项匹配code
     * @return
     */
    String dimCode() default "";

}
