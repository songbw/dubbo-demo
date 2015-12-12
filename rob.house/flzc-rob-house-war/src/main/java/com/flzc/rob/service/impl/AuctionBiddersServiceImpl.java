package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.AuctionBidders;
import com.flzc.rob.api.entity.PropertyCustomizationActivity;
import com.flzc.rob.api.service.AuctionBiddersService;
import com.flzc.rob.api.service.PropertyCustomizationActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 竞拍记录接口实现者
 * Created by iverson on 2015/10/27.
 */
@Service("auctionBiddersService")
public class AuctionBiddersServiceImpl implements AuctionBiddersService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(AuctionBiddersServiceImpl.class);
    @Autowired
    private CommonDao commonDao;

    @Autowired
    private BaseDao baseDao;

    @Autowired
    PropertyCustomizationActivityService customizationActivityService;

    @Override
    public Number save(AuctionBidders bidder) throws Exception {
        bidder.setCreateTime(new Date());
        try {
           return  this.commonDao.save(bidder);
        } catch (Exception e) {
            LOGGER.error("保存竞拍人异常",e);
            throw e;
        }

    }

    /**
     * 查询当前竞拍都，根据当前时间排序，取次高价
     *
     * @param activityId
     * @return
     */
    @Override
    public AuctionBidders queryCurBidder(Integer activityId) {
        Date now = new Date();
        String hql = "from AuctionBidders a  where a.createTime <=:now  order by a.id desc"  ;
        List<Object> result = null;
        try {
            result = this.baseDao.findByNamedParam(hql, "now", now, 2);
        } catch (Exception e) {
            LOGGER.error("查询当前出价人异常",e);
        }
        if(result==null || result.isEmpty()) return null;
        if(result.size()==1){
            return (AuctionBidders)result.get(0);
        }else if(result.size()==2){
            return (AuctionBidders)result.get(1);
        }else {
            return null;
        }

    }

    /**
     * 查询当前出价人数
     *
     * @param activityId
     * @return
     */
    @Override
    public int queryBiddersNum(Integer activityId) {
          String sql = "select count(a.user_id)  as total  from  auction_bidders a where a.activity_id = " + activityId;
          return    this.commonDao.findCountByHql(sql);
    }

    /**
     * 查询活动中标者
     *
     * @param activityId
     * @return
     */
    @Override
    public AuctionBidders queryWinner(Integer activityId) {
        String hql = " from AuctionBidders a  where a.createTime between :start  and  :end order by a.money desc  ";
        try {
            PropertyCustomizationActivity activity = this.customizationActivityService.queryById(activityId);
            Date actStartDate = activity.getActStartDate();
            Date actEndDate = activity.getActEndDate();
            String[] keys = {"start","end"};
            Object[] vals = {actStartDate,actEndDate};
            List<Object> rows = this.baseDao.findByNamedParam(hql, keys, vals , 1);
            if(rows != null && rows.size() > 0){
                return (AuctionBidders)rows.get(0);
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("查询竞拍活动异常",e);
            return null;
        }
    }
}
