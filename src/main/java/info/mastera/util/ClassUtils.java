package info.mastera.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassUtils {

    public static Type getGenericParameter(Class<?> cl) {
        return ((ParameterizedType) cl.getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
