package com.flzc.rob.api.service;

import com.flzc.rob.api.entity.AuctionBidders;

import java.util.List;

/**
 * 竞拍者竞拍记录
 * Created by iverson on 2015/10/27.
 */
public interface AuctionBiddersService {

    public Number save(AuctionBidders bidder) throws Exception;

    /**
     * 查询当前竞拍都，根据当前时间排序，取次高价
     * @return
     */
    public AuctionBidders queryCurBidder(Integer activityId);

    /**
     * 查询当前出价人数
     * @param activityId
     * @return
     */
    public int queryBiddersNum(Integer activityId);


    /**
     * 查询活动中标者
     * @param activityId
     * @return
     */
    public AuctionBidders queryWinner(Integer activityId);

}
