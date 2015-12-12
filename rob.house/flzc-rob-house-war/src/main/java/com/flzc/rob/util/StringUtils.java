package com.flzc.rob.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by iverson on 2015/10/12.
 */
public class StringUtils {
    /**
     * 把驼峰标识字符串转化成带下划线风格:如userId --> user_id
     * @param property
     * @return
     */
    public static String getColunmStyleStr(String property){
          if(org.apache.commons.lang.StringUtils.isBlank(property)) return property;
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
     * 把字符，数字数组联成串
     * @param list
     * @param separator，分隔符.默认是以,联接
     * @return
     */
    public static String joinArray(List<? extends Serializable> list , String separator){
        if(list == null || list.isEmpty()) return "";
        if(org.apache.commons.lang.StringUtils.isBlank(separator)) separator=",";
        StringBuffer sb  = new StringBuffer();
        for (Serializable i : list){
            sb.append(i).append(separator);
        }
        return sb.toString().replaceAll(separator + "$", "");
    }

}
