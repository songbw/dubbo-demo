package com.flzc.rob.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ArrayStack;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 反射帮助类
 * Created by iverson on 2015/10/10.
 */
public class ReflectionUtil {
    /**
     * 属性复制。把source里不为空的属性复制到dest对象中
     *
     * @param dest   目的对象
     * @param source 数据源对象
     * @param <T>
     */
    public static <T> void copyProperties(T dest, T source) throws Exception {
        if (dest == null || source == null) return;
        Map map = BeanUtils.describe(source);
        map.remove("class");
        if (map.isEmpty()) return;
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            if (val == null) continue;
            if (val instanceof String && val.toString().trim().isEmpty()) continue;
            BeanUtils.setProperty(dest, key.toString(), val);
        }

    }

    /**
     * 把map集合转化成一个具体实体类集合
     *
     * @param clazz
     * @param mapList
     * @param <T>
     * @return
     */
    public static <T> List<T> convertMaps2Entities(Class<T> clazz, List<Map<String, Object>> mapList) throws Exception {
        if (mapList == null) return Collections.EMPTY_LIST;
        T obj;
        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            obj = clazz.newInstance();
            BeanUtils.copyProperties(obj, map);
            list.add(obj);
        }
        return list;
    }
}
