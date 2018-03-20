package com.github.catalpaflat.valid.support;

import com.github.catalpaflat.valid.ParamValidProcessor;
import com.github.catalpaflat.valid.annotation.ParameterValid;
import com.github.catalpaflat.valid.constant.ParamValidConstant;
import com.github.catalpaflat.valid.exception.ParameterValidException;
import com.github.catalpaflat.valid.model.ParamTO;
import com.github.catalpaflat.valid.model.ParamValidTO;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ： CatalpaFlat
 * @date ：Create in 9:59 2018/1/17
 */
public class ParamValidSupport {
    private static ParamValidSupport mInstance;
    private Map<String, ParamValidProcessor> paramValidProcessorMap;
    private ParamTO paramTO;

    private ParamValidSupport(Map<String, ParamValidProcessor> paramValidProcessorMap) {
        this.paramValidProcessorMap = paramValidProcessorMap;
        if (paramTO == null) {
            try {
                File resource = ResourceUtils.getFile(ParamValidConstant.PARAM_VALID_YML_NAME);
                Yaml yaml = new Yaml();
                this.paramTO = yaml.loadAs(new FileInputStream(resource), ParamTO.class);
            } catch (FileNotFoundException e) {
                throw new ParameterValidException("Parameter validation does not exist.");
            }
        }
    }

    public static ParamValidSupport get(Map<String, ParamValidProcessor> paramValidProcessorMap) {
        if (mInstance == null) {
            synchronized (ParamValidSupport.class) {
                if (mInstance == null) {
                    mInstance = new ParamValidSupport(paramValidProcessorMap);
                }
            }
        }
        return mInstance;
    }

    /**
     * 校验
     */
    public List<String> validate(String className, String methodName,
                                 Class<?> annotationClass, Object[] args)
            throws NotFoundException, ClassNotFoundException {

        if (StringUtils.isBlank(className)) {
            return null;
        }
        if (StringUtils.isBlank(methodName)) {
            return null;
        }
        if (annotationClass == null) {
            return null;
        }

        ClassPool pool = ClassPool.getDefault();
        CtClass ct = pool.get(className);
        CtMethod ctMethod = ct.getDeclaredMethod(methodName);
        Object[][] parameterAnnotations = ctMethod.getParameterAnnotations();

        List<String> errorLists = new ArrayList<String>();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Object[] parameterAnnotation = parameterAnnotations[i];
            Object param = args[i];
            for (Object object : parameterAnnotation) {
                Annotation annotation = (Annotation) object;
                Class<? extends Annotation> aClass = annotation.annotationType();
                if (aClass.equals(annotationClass)) {
                    boolean isEmpty = ((ParameterValid) object).isEmpty();
                    boolean request = ((ParameterValid) object).request();
                    if (!request) {
                        break;
                    }
                    String type = typeCheck(param);
                    String name = type.toLowerCase() + ParamValidProcessor.class.getSimpleName();
                    if (isEmpty) {
                        ParamValidProcessor paramValidProcessor = paramValidProcessorMap.get(name);
                        boolean check = paramValidProcessor.validateType((ParameterValid) object, param);
                        if (!check) {
                            errorLists.add(param + ParamValidConstant.PARAM_TYPE_ERROR);
                            break;
                        } else {
                            String msg = paramValidProcessor.validate((ParameterValid) object, param);
                            if (StringUtils.isNotBlank(msg)) {
                                errorLists.add(msg);
                            }
                        }
                    }
                }
            }
        }
        if (errorLists.size() != 0) {
            return errorLists;
        }
        return null;
    }

    private String typeCheck(Object param) {
        Class<?> paramClass = param.getClass();
        String simpleName = paramClass.getSimpleName();
        List<String> types = paramTO.getTypes();
        List<ParamValidTO> valid = paramTO.getValid();
        String result = null;
        for (int i = 0; i < types.size(); i++) {
            String str = types.get(i);
            if (str.equals(simpleName)) {
                result = valid.get(i).getBeanPrefix();
                break;
            }
        }
        if (StringUtils.isBlank(result)) {
            throw new ParameterValidException("处理器" + paramClass + "不存在");
        }
        return result;
    }
}
