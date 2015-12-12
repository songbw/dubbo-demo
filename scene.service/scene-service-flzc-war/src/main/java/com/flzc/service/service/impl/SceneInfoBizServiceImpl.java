package com.flzc.service.service.impl;

import com.flzc.base.util.LinkEncrypt;
import com.flzc.base.util.Memcached;
import com.flzc.rob.api.entity.HouseBuildingInfo;
import com.flzc.rob.api.service.HouseBuildingService;
import com.flzc.scene.filing.api.entity.SceneInfo;
import com.flzc.scene.filing.api.service.SceneInfoService;
import com.flzc.service.bean.SceneInfoBean;
import com.flzc.service.common.MemcachedKeyConstant;
import com.flzc.service.exception.ErrorBean;
import com.flzc.service.exception.SceneException;
import com.flzc.service.exception.SceneResponseException;
import com.flzc.service.service.AuthenticateService;
import com.flzc.service.service.SceneInfoBizService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * 案场经纪人服务实现类
 * Created by song on 2015/11/19.
 */
@Service
public class SceneInfoBizServiceImpl implements SceneInfoBizService {

    private static  final Logger logger = LoggerFactory.getLogger(SceneInfoBizServiceImpl.class);

    @Autowired
    private SceneInfoService sceneInfoService ;

    @Autowired
    private HouseBuildingService houseBuildingService ;

    @Resource
    private AuthenticateService authenticateService ;
    /**
     * 经纪人登录服务
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public SceneInfoBean login(String username, String password) throws SceneException {
        SceneInfoBean sceneInfoBean = new SceneInfoBean() ;
        ErrorBean errorBean = new ErrorBean() ;
        HouseBuildingInfo houseBuildingInfo = new HouseBuildingInfo() ;
        SceneInfo sceneInfo = sceneInfoService.findByUsername(username) ;
        if (sceneInfo == null) {
            errorBean.setErrno("300022");
            errorBean.setMsg("用户名不存在！");
            throw new SceneResponseException(errorBean) ;
        }
        try {
           houseBuildingInfo = houseBuildingService.queryById(sceneInfo.getBuildingId()) ;
        } catch (Exception e) {
            logger.error("获取楼盘信息异常",e);
            errorBean.setErrno("300102");
            errorBean.setMsg("获取楼盘信息失败");
            throw new SceneException(errorBean) ;
        }
        if (sceneInfo == null) {
            // 返回异常信息
            errorBean.setErrno("300001");
            errorBean.setMsg("用户名不存在");
            throw new SceneException(errorBean) ;
        }
         String tokenId = "" ;
        if (password.equals(sceneInfo.getPassword())) {
            // id to tokenId
            try {
                tokenId = LinkEncrypt.generateToken(sceneInfo.getId().toString()) ;
//                BeanUtils.copyProperties(sceneInfoBean,sceneInfo);
                sceneInfoBean.setId(sceneInfo.getId());
                sceneInfoBean.setCreateTime(sceneInfo.getCreateTime());
                sceneInfoBean.setPhone(sceneInfo.getPhone());
                sceneInfoBean.setRealname(sceneInfo.getRealname());
                sceneInfoBean.setStatus(sceneInfo.getStatus());
                sceneInfoBean.setTel(sceneInfo.getTel());
                sceneInfoBean.setUsername(sceneInfo.getUsername());
                sceneInfoBean.setTokenId(tokenId);
                sceneInfoBean.setBuildingId(sceneInfo.getBuildingId());
                sceneInfoBean.setUsername(sceneInfo.getUsername());
            } catch (Exception e) {
                logger.error("获取tokenid失败！",e);
                errorBean.setErrno("300100");
                errorBean.setMsg("后台错误");
                throw new SceneException(errorBean) ;
            }
        } else {
            errorBean.setErrno("300004");
            errorBean.setMsg("密码错误");
            throw new SceneException(errorBean) ;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 30); //minus number would decrement the days
        Memcached.set(tokenId,sceneInfoBean.getId(),cal.getTime()) ;
        return sceneInfoBean;
    }

    /**
     * 经纪人退出登录
     *
     * @param tokenId
     * @return
     * @throws SceneException
     */
    @Override
    public String logout(String tokenId) throws SceneException {
        Memcached.remove(tokenId) ;
        return " {\"status\":0,\"success\":true}";
    }

    /**
     * 修改手机号
     *
     * @param tel
     * @param code
     * @return
     * @throws SceneException
     */
    @Override
    public String modifyTel(String token, String tel, String code) throws SceneException {
        String result = (String) Memcached.get(MemcachedKeyConstant.SCENE_MODIFY_TEL_CODE + tel);
        ErrorBean errorBean = new ErrorBean() ;
        if (result == null || "".equals(result) || (!code.equals(result))) {
            errorBean.setErrno("300006");
            errorBean.setMsg("验证码错误！");
            throw new SceneException(errorBean) ;
        }
        try {
            int id = (int) Memcached.get(token);
            SceneInfo sceneInfo = sceneInfoService.findById(id) ;
            sceneInfo.setPhone(tel);
            sceneInfoService.updateSceneInfo(sceneInfo);
        } catch (Exception e) {
            logger.error("后台服务异常",e);
            errorBean.setErrno("300100");
            errorBean.setMsg("后台服务异常！");
            throw new SceneException(errorBean) ;
        }
        return  " {\"status\":0,\"success\":true}";
    }

    /**
     * 修改密码
     *
     * @param token
     * @param tel
     * @param code
     * @param passwd
     * @return
     * @throws SceneException
     */
    @Override
    public String modifyPasswd(String token, String tel, String code, String passwd) throws SceneException {
        String result = (String) Memcached.get(MemcachedKeyConstant.SCENE_MODIFY_TEL_CODE + tel);
        ErrorBean errorBean = new ErrorBean() ;
        if (result == null || "".equals(result) || (!code.equals(result))) {
            errorBean.setErrno("300006");
            errorBean.setMsg("验证码错误！");
            throw new SceneException(errorBean) ;
        }
        try {
            int id = (int) Memcached.get(token);
            SceneInfo sceneInfo = sceneInfoService.findById(id) ;
            sceneInfo.setPassword(passwd);
            sceneInfoService.updateSceneInfo(sceneInfo);
        } catch (Exception e) {
            logger.error("后台服务异常",e);
            errorBean.setErrno("300100");
            errorBean.setMsg("后台服务异常！");
            throw new SceneException(errorBean) ;
        }
        return  " {\"status\":0,\"success\":true}";
    }
}
