package com.flzc.quartz.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by iverson on 2015/9/19.
 */
public class JsonUtils {
     public  static  final String SUCC= "succ";
    public  static  final String MSG= "msg";
    /**
     *  获取一个用于controller返回客户端的json对象
     *  对象里包含一个succ属性，默认为true
     * @return
     */
    public static JSONObject getDefaultJsonForWeb(){
        JSONObject  result = new JSONObject();
        result.put(SUCC,true);
        return result;
    }


}
