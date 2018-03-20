package com.github.catalpaflat.valid.annotation;

import java.lang.annotation.*;

/**
 * 校验注解
 *
 * @author ： CatalpaFlat
 * @date ：Create in 13:05 2018/1/16
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterValid {
    Class<?> type();

    String msg();

    boolean request() default true;

    boolean isEmpty() default true;

    boolean isBlank() default true;

    boolean isNull() default false;

    boolean isPhone() default false;

    int min() default 0;

    int max() default 0;

    int[] section() default {0, 0};

    boolean isMin() default false;

    boolean isMax() default false;

    boolean isSection() default false;
}
