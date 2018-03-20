package com.github.catalpaflat.valid.constant;

/**
 * @author CatalpaFlat
 * @date Created in 2018/3/18 下午3:47
 */
public final class ParamValidConstant {
    private ParamValidConstant() {
    }

    public static final String PARAM_VALID_YML_NAME = "classpath:parameters-verify.yml";

    public static final String PARAM_TYPE_ERROR = "param type error";

    public static final String INT_PARAM_ERROR = "Invalid interva";
    public static final int INT_PARAM_TYPE_MAX_SIZE = 2;
    public static final int INT_PARAM_SIZE_SUBSCRIPT_MIN = 0;
    public static final int INT_PARAM_SIZE_SUBSCRIPT_MAX = 1;


    public static final int STRING_SIZE = 2;
    public static final char STRING_TYPE_END = '}';
    public static final char STRING_TYPE_BEGIN = '{';
    public static final char STRING_EMPTY_DOUBLE_CHARACTER = '"';
    public static final char STRING_EMPTY_SINGLE_CHARACTER = '\'';
}
