package com.flzc.rob.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.AuctionActivityDeposit;
import com.flzc.rob.api.service.AuctionActivityDepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 竞拍保证金交纳记录接口实现类
 * Created by iverson on 2015/10/27.
 */
@Service("auctionActivityDepositService")
public class AuctionActivityDepositServiceImpl implements AuctionActivityDepositService{

    private  static  final Logger LOGGER = LoggerFactory.getLogger(AuctionActivityDepositServiceImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private BaseDao baseDao;

    /**
     * 保存
     *
     * @param deposit
     * @return
     */
    @Override
    public Number save(AuctionActivityDeposit deposit) throws Exception {
        try {
            deposit.setCreateTime(new Date());
            deposit.setStatus(0);
            return  this.commonDao.save(deposit);
        } catch (Exception e) {
            LOGGER.error("保存失败",e);
            throw  e;
        }
    }

    /**
     * 更新
     *
     * @param deposit
     */
    @Override
    public void update(AuctionActivityDeposit deposit) throws Exception {
        try {
            deposit.setUpdateTime(new Date());
            this.commonDao.update(deposit);
        } catch (Exception e) {
            LOGGER.error("更新失败",e);
            throw e;
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public AuctionActivityDeposit findById(Integer id) throws Exception {
        try {
            return this.commonDao.findById(AuctionActivityDeposit.class,id);
        } catch (Exception e) {
            LOGGER.error("根据id查询异常",e);
            throw e;
        }
    }

    /**
     * 根据活动id查询
     *
     * @param activityId
     * @return
     */
    @Override
    public List<AuctionActivityDeposit> queryByActivityId(Integer activityId,int curPage,int pageSize) throws Exception {
        AuctionActivityDeposit deposit = new AuctionActivityDeposit();
        deposit.setActivityId(activityId);
        return  this.queryByParams(deposit,curPage,pageSize);
    }

    /**
     * 根据用户Id查询
     *
     * @param activityId
     * @return
     */
    @Override
    public List<AuctionActivityDeposit> queryByUserId(Integer activityId , int curPage,int pageSize) throws Exception {
        AuctionActivityDeposit deposit = new AuctionActivityDeposit();
        deposit.setActivityId(activityId);
        return  this.queryByParams(deposit,curPage,pageSize);
    }

    /**
     * 根据参数查询
     *
     * @param params
     * @return
     */
    @Override
    public List<AuctionActivityDeposit> queryByParams(AuctionActivityDeposit params,int curPage,int pageSize) throws Exception {
        try {
           return   this.commonDao.findObjsWithPage(params, curPage, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询异常",e);
            throw  e;
        }
    }

    /**
     * 查询有多少人参与这当前活动
     *
     * @param activityId
     * @return
     */
    @Override
    public int queryBidders(Integer activityId) {

        String hql = "select count(id) as total  from  AuctionActivityDeposit t where t.activityId=%1s ";
        int pageTotalCount = this.baseDao.findPageTotalCount(String.format(hql, activityId));
        return pageTotalCount;
    }

	@Override
	public List<AuctionActivityDeposit> queryDepositList(
			AuctionActivityDeposit auctionActivityDeposit) throws Exception {
		try {
			
			return commonDao.findObjs(auctionActivityDeposit);
		} catch (Exception e) {
			LOGGER.error("根据条件查询竞拍活动异常",e);
			throw e;
		}
	}
    
}
