package com.flzc.quartz.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-8-25.
 */
public class CommonUtils {
    public static String[] listToStrArray(List<String> list) {
        if (list == null || list.isEmpty()) return null;

        String[] str = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i);
        }
        return str;
    }

    /**
     * 过滤掉map里的值为null,
     * @param map
     * @return
     */
    public static Map clearMapBlankVal(Map<String,?> map){
        if(map == null || map.isEmpty()) return map;
        map.remove("class");
        Iterator<? extends Map.Entry<String, ?>> iterator = map.entrySet().iterator();
        Map tmp = new HashMap<>();
        while (iterator.hasNext()){
            Map.Entry<String, ?> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value == null ||StringUtils.isBlank(value.toString())){
                value = null;
            }
            if(value != null){
                tmp.put(key,entry.getValue());
            }
        }
        return  tmp;
    }

    /**
     * 把驼峰标识字符串转化成带下划线风格:如userId --> user_id
     * @param property
     * @return
     */
    public static String getColunmStyleStr(String property){
        if(StringUtils.isBlank(property)) return property;
        char[] chars = property.toCharArray();
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ;i <chars.length;i++){
            if( Character.isUpperCase(chars[i]) ){
                sb.append("_" +  Character.toLowerCase(chars[i]) );
            }else {
                sb.append(chars[i] );
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param total 总条数
     * @param pageSize
     * @return 返回总页数
     */
    public static int getTotalPage(int total ,int pageSize){
        if(total==0) return 0;
        if(pageSize==0) pageSize = 10 ;
        int mod = total % pageSize;
        int page = total / pageSize;
        if(mod != 0){
            page ++;
        }
        return page;
    }

    public static void main(String[] args) {
        System.out.println(getTotalPage(101,10));
    }
}
