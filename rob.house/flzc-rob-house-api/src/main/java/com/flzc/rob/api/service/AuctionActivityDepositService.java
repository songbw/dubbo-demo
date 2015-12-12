package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.AuctionActivityDeposit;

import java.util.List;

/**
 * 竞拍保证金交纳记录表接口服务
 * Created by iverson on 2015/10/27.
 */
public interface AuctionActivityDepositService {

    /**
     * 保存
     * @param deposit
     * @return
     */
    public Number save(AuctionActivityDeposit deposit) throws Exception;

    /**
     * 更新
     * @param deposit
     */
    public void update(AuctionActivityDeposit deposit) throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public AuctionActivityDeposit findById(Integer id) throws Exception;

    /**
     * 根据活动id查询
     * @param activityId
     * @return
     */
    public List<AuctionActivityDeposit> queryByActivityId(Integer activityId, int curPage, int pageSize) throws Exception;

    /**
     * 根据用户Id查询
     * @param activityId
     * @return
     */
    public List<AuctionActivityDeposit> queryByUserId(Integer activityId, int curPage, int pageSize) throws Exception;

    /**
     * 根据参数查询
     * @param params
     * @return
     */
    public List<AuctionActivityDeposit> queryByParams(AuctionActivityDeposit params, int curPage, int pageSize) throws Exception;


    /**
     * 查询有多少人参与这当前活动
     * @param activityId
     * @return
     */
    public int queryBidders(Integer activityId);
    
    /**
     * 根据条件查询所有
     * @return
     * @throws Exception 
     */
    public List<AuctionActivityDeposit> queryDepositList(AuctionActivityDeposit auctionActivityDeposit) throws Exception;
}
