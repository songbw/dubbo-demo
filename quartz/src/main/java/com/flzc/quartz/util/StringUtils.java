package com.flzc.quartz.util;

/**
 * Created by iverson on 2015/9/19.
 */
public class StringUtils {

    public static boolean isBlank(String str){
          if(str == null || "".equals(str.trim())){
              return true;
          }
        return  false;
    }
}
