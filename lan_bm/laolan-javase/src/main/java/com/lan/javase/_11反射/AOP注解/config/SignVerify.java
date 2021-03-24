package com.sxsh.note;


import java.lang.annotation.*;

/**
 * sign验签注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface SignVerify {


}
