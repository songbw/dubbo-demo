package com.flzc.test;

import com.alibaba.fastjson.JSONObject;
import com.flzc.rob.api.entity.PropertyCustomizationRecord;
import com.flzc.rob.api.service.AutionPriceService;
import com.flzc.rob.api.service.PropertyCustomizationRecordService;
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
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring-dubbo-consumer.xml"})
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
//@Transactional
//@TransactionConfiguration(transactionManager = "txManager")
public class PropertyCustomizationTest {
    @Autowired
   private PropertyCustomizationRecordService recordService;

    @Test
    public void testSave() throws Exception {
        this.recordService.save(null);
    }

    @Test
    public void testQueryById(){
        try {
            PropertyCustomizationRecord record = this.recordService.queryById(1);
            System.out.println(JSONObject.toJSON(record));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
