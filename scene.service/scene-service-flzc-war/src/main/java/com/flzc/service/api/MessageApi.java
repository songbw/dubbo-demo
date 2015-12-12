package com.flzc.service.api;

import com.flzc.base.util.Memcached;
import com.flzc.service.bean.MessagesBean;
import com.flzc.service.bean.SceneFilingsBean;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.exception.SceneResponseException;
import com.flzc.service.service.MessageBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2015/11/30.
 */
@Component
@Scope
@Path("/messages")
public class MessageApi {
//    @Autowired
//    private MessageBizService messageBizService ;
//
//    @GET
//    @Path("/list")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_JSON)
//    public MessagesBean allMessage(@QueryParam("tokenId") String tokenId,@QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
//        ErrorBean errorBean = new ErrorBean() ;
//        MessagesBean messagesBean = new MessagesBean() ;
//        if (page ==null || page == 0) {
//            page = 1 ;
//        }
//        if (pageSize ==null || pageSize == 0) {
//            pageSize = 10 ;
//        }
//        List<MessageSystem> messageSystem = new ArrayList<MessageSystem>() ;
//        if (Memcached.keyExists(tokenId)) {
//            try {
//                messageSystem = messageBizService.getUserSystemMessageList(tokenId, page, pageSize) ;
//                messagesBean.setMessageSystems(messageSystem);
//            } catch (SceneException e) {
//                throw new SceneResponseException(e.getErrorBean()) ;
//            }
//        } else {
//            errorBean.setErrno("300101");
//            errorBean.setMsg("tokenId验证失败！");
//            throw new SceneResponseException(errorBean) ;
//        }
//        return messagesBean ;
//    }
}
