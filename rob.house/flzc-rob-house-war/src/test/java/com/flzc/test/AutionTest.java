package com.flzc.test;

import com.alibaba.fastjson.JSONObject;
import com.flzc.base.dao.BaseDao;
import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.BaseService;
import com.flzc.rob.api.entity.AuctionActivityDeposit;
import com.flzc.rob.api.entity.HomePageHeadline;
import com.flzc.rob.api.service.AuctionActivityDepositService;
import com.flzc.rob.api.service.AutionPriceService;
import com.flzc.rob.api.service.HomePageHeadlineService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015-8-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring-dubbo-consumer.xml"})
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class AutionTest {
    @Autowired
    private AutionPriceService autionPriceService;

    @Autowired
    private AuctionActivityDepositService depositService;

    @Autowired
    private HomePageHeadlineService headlineService;

    @Resource(name = "baseServiceImpl")
    private BaseService baseDao;

    @Autowired
    private CommonDao commonDao;


    @Test
    public void testQuery() throws Exception {

        Integer actId = 1;
        Integer count = this.autionPriceService.queryAutionPriceCountByActId(actId);
        Assert.assertEquals(1,count.intValue());
    }
    @Test
    public void testQueryHeadline(){
        List<HomePageHeadline> homePageHeadlines = this.headlineService.queryByLimit(10);
        System.out.println(homePageHeadlines.size());
    }

    @Test
    public void testQueryDeposit() throws Exception {
        List<AuctionActivityDeposit> auctionActivityDeposits = this.depositService.queryByActivityId(1, 1, 10);
        int count = this.depositService.queryBidders(1);
        System.out.println(count);
        Assert.assertEquals(1, auctionActivityDeposits.size());
    }
    @Test
    public void test(){
       Map mapBySql = this.commonDao.findUniqueByHql("select id as b , activityName as a   from  AutionActivityInfo where id = 1");
      // Map mapBySql = this.baseDao.findMapBySql("select id , activity_name as name  from  aution_activity_info where id = 1");
     //   Map mapBySql = this.commonDao.findUniqueBySql("select id , activity_name as name  from  aution_activity_info where id = 1");
        System.out.println(JSONObject.toJSONString(mapBySql));
    }
}
