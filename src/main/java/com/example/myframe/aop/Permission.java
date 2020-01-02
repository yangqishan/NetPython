package com.example.myframe.aop;
/**
 *自定义接口（Permission）注解
 * desc:自定义权限管理注解
 */

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    String authorities() default "我是默认值";
}
