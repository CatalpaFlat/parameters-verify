package com.github.catalpaflat.valid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * path和query校验注解
 *
 * @author ： CatalpaFlat
 * @date ：Create in 9:48 2018/1/17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PathWithQueryParamValid {
}
