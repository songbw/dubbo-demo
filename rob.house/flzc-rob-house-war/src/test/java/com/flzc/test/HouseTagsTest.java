package com.flzc.test;

import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.rob.api.service.AutionPriceService;
import com.flzc.rob.api.service.HouseBuildingTagsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2015-8-22.
 */
@RunWith(MyJunitSpringRunner.class)
//@ContextConfiguration({"classpath:spring-dubbo-consumer.xml"})
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
//@Transactional
//@TransactionConfiguration(transactionManager = "txManager")
public class HouseTagsTest {
    @Autowired
    private HouseBuildingTagsService tagsService;                         ;

    @Test
    public void testQuery() throws Exception {
        List<HouseBuildingTags> count = this.tagsService.queryTags(1, "100");
        Assert.assertEquals(2,count.size());
    }

}
