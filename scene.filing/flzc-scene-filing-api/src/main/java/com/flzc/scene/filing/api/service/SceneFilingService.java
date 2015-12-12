package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.SceneFiling;

import java.util.List;

/**
 *  报备服务
 * Created by song on 2015/11/18.
 */
public interface SceneFilingService {

    /**
     *  添加报备信息
     * @param filing
     * @return
     */
    public SceneFiling saveSceneFiling(SceneFiling filing);

    /**
     *  更新报备信息
     * @param filing
     */
    public void updateSceneFiling(SceneFiling filing);

    /**
     *  根据ID查询指定报备信息
     * @param id
     * @return
     */
    public SceneFiling findById(Integer id) ;

    /**
     *  根据案场ID和报备状态查询报备信息列表
     * @param sceneId
     * @param status
     * @return
     */
    public List<SceneFiling> findbyStatus(Integer sceneId, Integer status, Integer page, Integer pageSize);

    /**
     *   根据案场ID和报备状态获取总条数
     * @param sceneId
     * @param status
     * @return
     */
    public int allByStatus(Integer sceneId, Integer status) ;

    /**
     *  根据用户ID查询经纪人列表
     * @param sceneId
     * @param userId
     * @return
     */
    public List<SceneFiling> findAgencyListByUserId(Integer sceneId, Integer userId) ;

    /**
     *  获取已看房经纪人名称
     * @param sceneId
     * @param userId
     * @return
     */
    public List<SceneFiling> findAgencyBy4UserId(Integer sceneId, Integer userId) ;

    /**
     *  获取待看信息列表
     * @param sceneId
     * @param status
     * @return
     */
    public List<SceneFiling> findbyReserver(Integer sceneId, Integer status, Integer page, Integer pageSize) ;

    /**
     *  获取待看信息条数
     * @param sceneId
     * @param status
     * @return
     */
    public int allByReserve(Integer sceneId, Integer status) ;

    /**
     *   根据客户状态、来源、活动信息等查询客户管理列表
     *
     * @param sceneId
     * @param status
     * @param activityId
     * @param intentId
     * @param page
     * @param pageSize
     * @return
     */
    public  List<SceneFiling> queryClient(Integer sceneId,Integer status,Integer activityId, Integer intentId, Integer page, Integer pageSize) ;

    /**
     *    根据姓名查询该楼盘用户列表
     * @param sceneId
     * @param name
     * @param page
     * @param pageSize
     * @return
     */
    public List<SceneFiling> findByName(Integer sceneId, String name, Integer page, Integer pageSize);

    /**
     *   根据用户状态查询报备信息
     * @param sceneId
     * @param userId
     * @param status
     * @return
     */
    public List<SceneFiling> findByUserstatus(Integer sceneId, Integer userId, Integer status) ;

}
