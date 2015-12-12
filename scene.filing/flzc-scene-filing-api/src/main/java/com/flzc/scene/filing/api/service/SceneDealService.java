package com.flzc.scene.filing.api.service;

import com.flzc.scene.filing.api.entity.*;

/**
 *  案场成交服务
 * Created by song on 2015/11/18.
 */
public interface SceneDealService {
    /**
     *  保存案场成交信息
     * @param sceneDeal
     * @return
     */
    public SceneDeal saveSceneDeal(SceneDeal sceneDeal);

    /**
     *  根据ID查询指定信息
     * @param id
     * @return
     */
    public SceneDeal findByDealId(Integer id)  ;

    /**
     *  根据CODE获取指定房链券信息
     * @param code
     * @return
     */
    public FlzcTicket findByTicketCode(String code) ;

    /**
     *  获取用户信息
     * @param id
     * @return
     */
    public User findUserById(Integer id) ;

    /**
     *  根据活动ID和活动类型，查询活动汇总表
     * @param id
     * @param type
     * @return
     */
    public ActivityRecap findActivityByIdAndType(Integer id, Integer type) ;

    /**
     *  根据ID查询楼盘信息
     * @param id
     * @return
     */
    public HouseBuildingInfo findBuildingById(Integer id) ;
}
