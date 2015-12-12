package com.flzc.service.service;

import com.flzc.scene.filing.api.entity.SceneFiling;
import com.flzc.service.bean.SceneInfoBean;
import com.flzc.service.exception.SceneException;

/**
 *  案场经纪人业务接口
 * Created by song on 2015/11/19.
 */
public interface SceneInfoBizService {
    /**
     *   经纪人登录服务
     * @param username
     * @param password
     * @return
     */
    public SceneInfoBean login(String username, String password) throws SceneException;

    /**
     *  经纪人退出登录
     * @param tokenId
     * @return
     * @throws SceneException
     */
    public String logout(String tokenId) throws SceneException ;

    /**
     *  修改手机号
     * @param tel
     * @param code
     * @return
     * @throws SceneException
     */
    public String modifyTel(String Token, String tel, String code) throws SceneException ;

    /**
     *  修改密码
     * @param token
     * @param tel
     * @param code
     * @param passwd
     * @return
     * @throws SceneException
     */
    public String modifyPasswd(String token, String tel, String code,String passwd) throws SceneException ;

}
