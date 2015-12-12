package com.flzc.service.api;

import com.flzc.base.util.Memcached;
import com.flzc.service.bean.SceneInfoBean;
import com.flzc.service.bean.SceneInfoView;
import com.flzc.service.common.Constant;
import com.flzc.service.common.MemcachedKeyConstant;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.exception.SceneResponseException;
import com.flzc.service.service.AuthenticateService;
import com.flzc.service.service.SceneInfoBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;

/**
 *   案场信息API
 * Created by song on 2015/11/19.
 */
@Component
@Scope
@Path("/sceneinfo")
public class SceneInfoApi {
    private static  final Logger LOGGER = LoggerFactory.getLogger(SceneInfoApi.class) ;

    @Autowired
    private SceneInfoBizService sceneInfoService ;

    @Resource
    private AuthenticateService authenticateService ;

    /**
     *   案场经纪人登录
     * @param username
     * @param password
     * @return
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public SceneInfoView sceneLogin(@FormParam("username") String username, @FormParam("password") String password){
        LOGGER.error(username+"   " + password);
        ErrorBean errorBean = new ErrorBean() ;
        SceneInfoView sceneInfoView = new SceneInfoView() ;
        if (username == null || "".equals(username)) {
            errorBean.setErrno("300002");
            errorBean.setMsg("用户名不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        if (password == null || "".equals(password)) {
            errorBean.setErrno("300003");
            errorBean.setMsg("密码不能为空");
            throw new SceneResponseException(errorBean) ;
        }
        SceneInfoBean sceneInfoBean = null;
        try {
            sceneInfoBean = sceneInfoService.login(username,password);
        } catch (SceneException e) {
            throw new SceneResponseException(e.getErrorBean()) ;
        }
        sceneInfoView.setSceneInfoBean(sceneInfoBean);
        return sceneInfoView;
    }

    /**
     *  推出登录
     * @param tokenId
     * @return
     */
    @Path("/logout")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sceneLogout(@QueryParam("tokenId") String tokenId) {
        LOGGER.error(tokenId);
        String result = "";
        ErrorBean errorBean = new ErrorBean() ;
        if (Memcached.keyExists(tokenId)) {
            try {
                result = sceneInfoService.logout(tokenId) ;
            } catch (SceneException e) {
                errorBean.setErrno("300100");
                errorBean.setMsg("后台错误");
                throw new SceneResponseException(errorBean) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result ;
    }

    /**
     *   发送验证码
     * @param tokenId
     * @param tel
     * @return
     */
    @GET
    @Path("/sendcode")
    @Produces(MediaType.APPLICATION_JSON)
    public  String sendCode(@QueryParam("tokenId") String tokenId, @QueryParam("tel") String tel) {
        LOGGER.error(tokenId + "   " + tel);
        String result = "";
        ErrorBean errorBean = new ErrorBean() ;
        if (Memcached.keyExists(tokenId)) {
            result = authenticateService.creatCaptcha(tel) ;
            if (result == null || "".equals(result)) {
                errorBean.setErrno("300005");
                errorBean.setMsg("验证码失败！");
                throw new SceneResponseException(errorBean) ;
            } else {
                Memcached.set(MemcachedKeyConstant.SCENE_MODIFY_TEL_CODE + tel, result.toLowerCase(), Calendar.MINUTE, Constant.expire_time_5);
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return " {\"success\":true}";
    }

    /**
     *   修改手机号
     * @param tokenId
     * @param tel
     * @param code
     * @return
     */
    @Path("/update/tel")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTel(@FormParam("tokenId") String tokenId, @FormParam("tel") String tel, @FormParam("code") String code) {
        LOGGER.error(tokenId + "    " + tel + "     "+ code );
        ErrorBean errorBean = new ErrorBean() ;
        String result = "";
        if (Memcached.keyExists(tokenId)) {
            if (tel == null || "".equals(tel)) {
                errorBean.setErrno("300006");
                errorBean.setMsg("手机号不能为空");
                throw new SceneResponseException(errorBean) ;
            }
            if (code == null || "".equals(code)) {
                errorBean.setErrno("300007");
                errorBean.setMsg("验证码不能为空");
                throw new SceneResponseException(errorBean) ;
            }
            try {
                result = sceneInfoService.modifyTel(tokenId,tel,code) ;
            } catch (SceneException e) {
               throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result ;
    }

    /**
     * 修改密码
     * @param tokenId
     * @param tel
     * @param code
     * @param password
     * @return
     */
    @Path("/update/passwd")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public String updatePasswd(@FormParam("tokenId") String tokenId, @FormParam("tel") String tel, @FormParam("code") String code, @FormParam("password") String password) {
        LOGGER.error(tokenId + "     " + tel + "     " + code + "    " + password);
        ErrorBean errorBean = new ErrorBean() ;
        String result = "";
        if (Memcached.keyExists(tokenId)) {
            if (tel == null || "".equals(tel)) {
                errorBean.setErrno("300006");
                errorBean.setMsg("手机号不能为空");
                throw new SceneResponseException(errorBean) ;
            }
            if (code == null || "".equals(code)) {
                errorBean.setErrno("300007");
                errorBean.setMsg("验证码不能为空");
                throw new SceneResponseException(errorBean) ;
            }
            if (password == null || "".equals(password)) {
                errorBean.setErrno("300008");
                errorBean.setMsg("密码不能为空");
                throw new SceneResponseException(errorBean) ;
            }
            try {
                result = sceneInfoService.modifyTel(tokenId,tel,code) ;
            } catch (SceneException e) {
                throw new SceneResponseException(e.getErrorBean()) ;
            }
        } else {
            errorBean.setErrno("300101");
            errorBean.setMsg("tokenId验证失败！");
            throw new SceneResponseException(errorBean) ;
        }
        return result ;
    }

}
