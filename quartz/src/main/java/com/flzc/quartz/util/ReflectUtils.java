package com.flzc.quartz.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by iverson on 2015/11/26.
 */
public class ReflectUtils {
    public static <T> List<T> convert(Class<T> clazz, List<Map<String, Object>> maps) throws Exception {
        if (clazz == null) throw new Exception("参数为空");
        if (maps == null || maps.isEmpty()) return Collections.EMPTY_LIST;
        List<T> list = new ArrayList<>();
        for (Map m : maps) {
            T bean = (T) clazz.newInstance();
            BeanUtils.populate(bean, m);
            list.add(bean);
        }
        return list;
    }

}
