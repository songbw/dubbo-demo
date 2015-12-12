package com.flzc.service.common;

import com.alibaba.fastjson.JSONObject;
/**
 * Created by song on 2015/12/3.
 */
public class ResponseResult {

    public static String returnResult(Integer status){
        JSONObject result = new JSONObject();
        result.put(Constant.RESULT_FIELD_STATUS, status);
        result.put(Constant.RESULT_FIELD_MSG, Constant.RESULT_MSG.get(status));
        return result.toJSONString();
    }

    public static String returnResult(Integer status, Object data){
        JSONObject result = new JSONObject();
        result.put(Constant.RESULT_FIELD_STATUS, status);
        result.put(Constant.RESULT_FIELD_DATA, data);
        return result.toJSONString();
    }
}
