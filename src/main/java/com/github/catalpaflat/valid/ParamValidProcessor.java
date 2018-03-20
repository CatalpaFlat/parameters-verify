package com.github.catalpaflat.valid;


import com.github.catalpaflat.valid.annotation.ParameterValid;

/**
 * @author ： CatalpaFlat
 * @date ：Create in 13:59 2018/1/17
 */
public interface ParamValidProcessor {

    boolean validateType(ParameterValid parameterValid, Object param);

    String validate(ParameterValid parameterValid, Object param);
}
