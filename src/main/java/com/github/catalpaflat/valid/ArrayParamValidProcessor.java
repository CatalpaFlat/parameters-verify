package com.github.catalpaflat.valid;

import com.github.catalpaflat.valid.annotation.ParameterValid;
import com.github.catalpaflat.valid.constant.ParamValidConstant;
import com.github.catalpaflat.valid.exception.ParameterValidException;

/**
 * @author ： CatalpaFlat
 * @date ：Create in 14:19 2018/1/17
 */
public class ArrayParamValidProcessor extends AbstractParamValidProcessor {

    @Override
    public String validate(ParameterValid parameterValid, Object param) {
        if (param instanceof String[]) {

            String[] paramStr = (String[]) param;
            if (paramStr.length < 1) {
                return super.msg;
            }
        } else if (param instanceof Integer[]) {
            Integer[] paramInt = (Integer[]) param;
            int length = paramInt.length;
            if (length < 1) {
                return super.msg;
            }
            if (parameterValid.isSection()) {
                int[] section = parameterValid.section();
                if (section.length != ParamValidConstant.INT_PARAM_TYPE_MAX_SIZE) {
                    throw new ParameterValidException(ParamValidConstant.INT_PARAM_ERROR);
                }
                int max = section[ParamValidConstant.INT_PARAM_SIZE_SUBSCRIPT_MAX];
                int min = section[ParamValidConstant.INT_PARAM_SIZE_SUBSCRIPT_MIN];
                for (Integer parm : paramInt) {
                    if (parm > max) {
                        return super.msg;
                    }
                    if (parm < min) {
                        return super.msg;
                    }
                }
            }
        }
        return null;
    }
}
