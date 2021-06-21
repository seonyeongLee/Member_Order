package com.api.sy.common.utility;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;

@Slf4j
public abstract class ObjectUtility {
    final static public String SESSION_MEMBER ="MEMBER";

    public static boolean isEmpty(Object param) {
        if (param == null) {
            return true;
        }

        if (param instanceof String) {
            return ((String) param).length() == 0 || "".equals(param.toString());
        }

        if (param instanceof Collection) {
            return ((Collection<?>) param).isEmpty();
        }

        if (param instanceof Map) {
            return ((Map<?, ?>) param).isEmpty();
        }

        if (param instanceof Object[]) {
            return ((Object[]) param).length == 0;
        }

        return false;
    }

    public static boolean isNotEmpty(Object param) {
        return !ObjectUtility.isEmpty(param);
    }

}
