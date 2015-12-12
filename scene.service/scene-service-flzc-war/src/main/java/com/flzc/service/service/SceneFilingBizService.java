package com.flzc.service.service;

import com.flzc.scene.filing.api.entity.SceneFiling;
import com.flzc.scene.filing.api.entity.SceneInvalidReason;
import com.flzc.service.bean.SceneFilingsBean;
import com.flzc.service.exception.SceneException;

import java.util.Date;
import java.util.List;

/**
 *   案场报备业务服务
 * Created by song on 2015/11/20.
 */
public interface SceneFilingBizService {

    /**
     *  经纪人向案场经纪人提交报备
     * @param sceneFiling
     * @return
     */
    public String addSceneFiling(SceneFiling sceneFiling) throws SceneException;

    /**
     *  案场经纪人有效无效操作
     * @param token
     * @param fillingId
     * @param valid
     * @return
     */
    public String filingValid(String token, Integer fillingId, Integer valid) throws SceneException;

    /**
     *   修改看房时间
     * @param token
     * @param fillingId
     * @param reserce
     * @return
     * @throws SceneException
     */
    public String updateReserve(String token, Integer fillingId, Long reserce) throws SceneException;

    /**
     *  查看指定报备信息详情
     * @param token
     * @param fillingId
     * @return
     */
    public SceneFiling queryFilingInfo(String token, Integer fillingId) throws SceneException;

    /**
     * 查询待报备列表
     *
     * @param token
     * @param page
     * @return
     */
    public SceneFilingsBean allFiling(String token,  Integer page, Integer pageSize) throws SceneException;

    /**
     *  添加无效报备原因
     * @param token
     * @param sceneInvalidReason
     * @return
     */
    public String addInvalidReason(String token, SceneInvalidReason sceneInvalidReason) throws SceneException;

    /**
     *  根据用户ID获取经济人列表
     * @param token
     * @param userId
     * @return
     * @throws SceneException
     */
    public SceneFilingsBean getAgencyList(String token, Integer userId) throws SceneException ;

    /**
     *  看房服务
     * @param token
     * @return
     * @throws SceneException
     */
    public String userVisted(String token, Integer filingId) throws SceneException ;

    /**
     *  爽约服务
     * @param token
     * @param filingId
     * @return
     * @throws SceneException
     */
    public String userMissed(String token, Integer filingId,String missReason) throws SceneException ;

    /**
     *  备注
     * @param token
     * @param filingId
     * @param remark
     * @return
     * @throws SceneException
     */
    public String remark(String token, Integer filingId,String remark) throws SceneException ;

    /**
     * 查询已报备列表
     *
     * @param token
     * @param cp
     * @return
     */
    public SceneFilingsBean allAwaitVisit(String token, Integer cp, Integer ls) throws SceneException;

    /**
     *   客户管理列表
     * @param token
     * @param status
     * @param activityId
     * @param intentId
     * @param cp
     * @param ls
     * @return
     * @throws SceneException
     */
    public SceneFilingsBean clientManage(String token,Integer status,Integer activityId, Integer intentId, Integer cp, Integer ls) throws  SceneException ;

    /**
     *  根据用户姓名和案场ID查询用户信息
     * @param token
     * @param name
     * @param cp
     * @param ls
     * @return
     */
    public SceneFilingsBean clientFindByName(String token, String name, Integer cp, Integer ls) throws SceneException;
}
