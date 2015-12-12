package com.flzc.service.api;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.flzc.service.common.Constant;
import com.flzc.service.common.ResponseResult;
import com.flzc.service.service.MessageBizService;
/**
 * 消息
 * Created by song on 2015/12/3.
 */
@Component
@Scope
@Path("/message")
public class SceneMessageApi {

    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneMessageApi.class);

    @Autowired
    private MessageBizService messageBizService;

    @Path("/list")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces({"application/json;charset=UTF-8"})
    public String getUserMessageList(@FormParam("tokenId") String tokenId,
                                     @FormParam("recType") Integer recType,
                                     @FormParam("curPage") Integer curPage,
                                     @FormParam("pageSize") Integer pageSize){

        JSONObject result = new JSONObject();
        try{

            Map<String, Object> messageMap = messageBizService.getUserMessageList(tokenId, recType, curPage, 10);
            result.put(Constant.RESULT_FIELD_STATUS, messageMap.get(Constant.RESULT_FIELD_STATUS));
            result.put(Constant.RESULT_FIELD_MSG, messageMap.get(Constant.RESULT_FIELD_MSG));
            result.put(Constant.RESULT_FIELD_DATA, messageMap.get(Constant.RESULT_FIELD_DATA));
            return result.toJSONString();
        }catch(Exception e){
            LOGGER.error("获取用户消息异常",e.getMessage());
            e.printStackTrace();
            return  ResponseResult.returnResult(Constant.RESULT_MSG_1);
        }
    }

    @Path("/getUnReadMessageNumber")
    @GET
    @Produces({"application/json;charset=UTF-8"})
    public String getUserUnReadMessageNumber(@QueryParam("tokenId") String tokenId,
                                             @QueryParam("recType") Integer recType){

        try{

            Map<String, Object> unReadMap = messageBizService.getUserUnReadMessageNumber(tokenId,recType);
            return  ResponseResult.returnResult(Constant.RESULT_MSG_0,unReadMap);
        }catch(Exception e){
            LOGGER.error("获取用户未读消息异常",e.getMessage());
            e.printStackTrace();
            return  ResponseResult.returnResult(Constant.RESULT_MSG_1);
        }
    }

    @Path("/updateMessageStatus")
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces({"application/json;charset=UTF-8"})
    public String updateUserMessageStatus(@FormParam("tokenId") String tokenId,
                                          @FormParam("recType") Integer recType){

        try{

            messageBizService.updateUserMessageStatus(tokenId,recType);
            return  ResponseResult.returnResult(Constant.RESULT_MSG_0);
        }catch(Exception e){
            LOGGER.error("更新用户未读消息异常",e.getMessage());
            e.printStackTrace();
            return  ResponseResult.returnResult(Constant.RESULT_MSG_1);
        }
    }
}
