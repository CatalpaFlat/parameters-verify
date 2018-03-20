package com.github.catalpaflat.valid;

import com.github.catalpaflat.valid.annotation.ParameterValid;
import lombok.extern.slf4j.Slf4j;

import static com.github.catalpaflat.valid.constant.ParamValidConstant.PARAM_TYPE_ERROR;

/**
 * @author ： CatalpaFlat
 * @date ：Create in 14:01 2018/1/17
 */
public abstract class AbstractParamValidProcessor implements ParamValidProcessor {

    protected Class<?> paramClass;
    protected String msg;

    public boolean validateType(ParameterValid parameterValid, Object param) {
        Class<?> type = parameterValid.type();
        Class<?> paramClass = param.getClass();
        this.paramClass = paramClass;
        this.msg = parameterValid.msg();
        if (!type.equals(paramClass)) {
            return false;
        }
        return true;
    }

    /**
     * 处理器校验
     *
     * @param parameterValid 注解
     * @param param          参数
     * @return 校验结果
     */
    public abstract String validate(ParameterValid parameterValid, Object param);
}
