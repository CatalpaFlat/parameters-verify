package com.github.catalpaflat.valid;


import com.github.catalpaflat.valid.annotation.ParameterValid;
import com.github.catalpaflat.valid.utils.ValidateUtil;
import com.github.catalpaflat.valid.constant.ParamValidConstant;
import org.apache.commons.lang.StringUtils;

/**
 * @author ： CatalpaFlat
 * @date ：Create in 14:17 2018/1/17
 */
public class StringParamValidProcessor extends AbstractParamValidProcessor {

    @Override
    public String validate(ParameterValid parameterValid, Object param) {
        String paramStr = (String) param;
        if (parameterValid.isNull()) {
            if (StringUtils.isEmpty(paramStr)) {
                return super.msg;
            }
        } else {
            if (parameterValid.isBlank()) {
                if (StringUtils.isBlank(paramStr)) {
                    return super.msg;
                } else {
                    int length = paramStr.length();
                    char begin = paramStr.charAt(0);
                    char end = paramStr.charAt(length - 1);
                    if (ParamValidConstant.STRING_TYPE_BEGIN == begin &&
                            ParamValidConstant.STRING_TYPE_END == end) {
                        return super.msg;
                    }
                    if (length == ParamValidConstant.STRING_SIZE && ParamValidConstant.STRING_EMPTY_DOUBLE_CHARACTER == begin
                            && ParamValidConstant.STRING_EMPTY_DOUBLE_CHARACTER == end) {
                        return super.msg;
                    }
                    if (length == ParamValidConstant.STRING_SIZE && ParamValidConstant.STRING_EMPTY_SINGLE_CHARACTER == begin
                            && ParamValidConstant.STRING_EMPTY_SINGLE_CHARACTER == end) {
                        return super.msg;
                    }
                    boolean phone = parameterValid.isPhone();
                    if (phone) {
                        boolean mobileNO = ValidateUtil.isMobile(paramStr);
                        if (!mobileNO) {
                            return "手机号格式不正确";
                        }
                    }
                }
            }
        }
        return null;
    }

}
