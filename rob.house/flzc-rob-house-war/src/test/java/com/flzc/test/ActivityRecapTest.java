package com.flzc.test;

import com.flzc.rob.api.entity.ActivityRecap;
import com.flzc.rob.api.entity.HouseBuildingInfo;
import com.flzc.rob.api.service.ActivityRecapService;
import com.flzc.rob.api.service.HouseBuildingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by Administrator on 2015-8-22.
 */
@RunWith(MyJunitSpringRunner.class)
//@ContextConfiguration({"classpath:spring-dubbo-consumer.xml"})
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
//@Transactional
//@TransactionConfiguration(transactionManager = "txManager")
public class ActivityRecapTest {
    @Autowired
    private ActivityRecapService activityRecapService;
    
    @Test
    public void testQueryAboutOnLineActivityCount(){
//    	Integer a = this.activityRecapService.queryAboutOnLineActivityCount();
//    	System.out.println(a);
    }
    
    @Test
    public void testQueryActivityListByPage(){
//    	List<ActivityRecap> a = this.activityRecapService.queryActivityListByPage(1, 20);
//    	System.out.println(a.size());
    }
    
    
}
