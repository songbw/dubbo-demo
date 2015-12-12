package com.flzc.test;

import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.rob.api.entity.HouseInfo;
import com.flzc.rob.api.service.HouseBuildingTagsService;
import com.flzc.rob.api.service.HouseInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * Created by Administrator on 2015-8-22.
 */
@RunWith(MyJunitSpringRunner.class)
//@ContextConfiguration({"classpath:spring-dubbo-consumer.xml"})
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
//@Transactional
//@TransactionConfiguration(transactionManager = "txManager")
public class HouseInfoTest {
    @Autowired
    private HouseInfoService houseInfoService;                         ;

    @Test
    public void testQuery() throws Exception {
        List<HouseInfo> count = this.houseInfoService.queryByBuildingId(6);
        Assert.assertEquals(1,count.size());
    }

}
