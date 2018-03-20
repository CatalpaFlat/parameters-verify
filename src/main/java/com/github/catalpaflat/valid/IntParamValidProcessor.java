package com.github.catalpaflat.valid;

import com.github.catalpaflat.valid.annotation.ParameterValid;
import com.github.catalpaflat.valid.exception.ParameterValidException;

import static com.github.catalpaflat.valid.constant.ParamValidConstant.*;

/**
 * @author ： CatalpaFlat
 * @date ：Create in 14:04 2018/1/17
 */
public class IntParamValidProcessor extends AbstractParamValidProcessor {

    @Override
    public String validate(ParameterValid parameterValid, Object param) {
        if (param instanceof Integer) {
            Integer paramInt = (Integer) param;
            if (parameterValid.isMin() && paramInt < parameterValid.min()) {
                return super.msg;
            }
            if (parameterValid.isMax() && paramInt < parameterValid.max()) {
                return super.msg;
            }
            if (parameterValid.isSection()) {
                int[] section = parameterValid.section();
                if (section.length != INT_PARAM_TYPE_MAX_SIZE) {
                    throw new ParameterValidException(INT_PARAM_ERROR);
                }
                int sectionMin = section[INT_PARAM_SIZE_SUBSCRIPT_MIN];
                int sectionMax = section[INT_PARAM_SIZE_SUBSCRIPT_MAX];
                if (!((paramInt >= sectionMin) && (paramInt <= sectionMax))) {
                    return super.msg;
                }
            }
        }

        return null;
    }
}
