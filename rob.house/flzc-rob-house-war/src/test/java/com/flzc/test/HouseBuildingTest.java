package com.flzc.test;

import com.alibaba.fastjson.JSONObject;
import com.flzc.rob.api.entity.HouseBuildingInfo;
import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.rob.api.service.HomePageFeatureTagService;
import com.flzc.rob.api.service.HouseBuildingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Administrator on 2015-8-22.
 */
@RunWith(MyJunitSpringRunner.class)
//@ContextConfiguration({"classpath:spring-dubbo-consumer.xml"})
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
//@Transactional
//@TransactionConfiguration(transactionManager = "txManager")
public class HouseBuildingTest {
    @Autowired
    private HouseBuildingService buildingService;
    @Autowired
    private HomePageFeatureTagService featureTagService;

    @Test
    public void testSave() throws Exception {
        HouseBuildingInfo info = new HouseBuildingInfo();
        info.setCreateTime(new Date());
        info.setName("好大一一个楼盘");
        info.setAreaId(6);
        info.setBuildStage(12001);
        info.setDecoration("无装修");
        info.setDikouTicket("xxxx");
        info.setFinishDate(new Date());
        this.buildingService.save(info);
    }
    @Test
    public void testQuery() throws Exception {
        HouseBuildingInfo info = new HouseBuildingInfo();
        info.setAreaId(6);
        List<String> cols = new ArrayList<>();
        cols.add("id");
        this.buildingService.queryListByParams(info);
    }
    @Test
    public void testQueryById() throws Exception {
        try {
            HouseBuildingInfo obj = this.buildingService.queryById(2);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryList(){
        Map<String,Object> param = new HashMap<>();
    //    param.put("areaId" ,6);
        param.put("latitude",28.2278784682);
        param.put("longitude",112.9388157641 );
//        param.put("referPrice" ,"1000-9000");
//        param.put("around" ,"34");
       param.put("around" ,"");
//        param.put("feature" ,"12");
//        param.put("buildingType" ,"13");
//        param.put("hotTag1" ,"205");
        List<Map<String, Object>> maps = this.buildingService.queryList(param, 1, 10);
        Assert.assertEquals(  1,maps.size()    );
    }
    @Test
    public void testQueryAgencyList(){
        Map<String,Object> param = new HashMap<>();
        param.put("areaId" ,6);
        param.put("actType" ,1);
        param.put("distance" ,100);
        param.put("orderFlag" ,2);
        param.put("latitude",28.2278784682);
        param.put("longitude",112.9388157641 );
        List<Map<String, Object>> maps = this.buildingService.queryListForAgency(param, 1, 10);
        Assert.assertEquals(1, maps.size());
    }
    @Test
    public void testQueryByActAndCity(){
        Map<String,Object> params = new HashMap<>();
        params.put("cityName","北京市");
       // params.put("actType",39003);
        params.put("actType",39002);
       // params.put("actType",39001);
        params.put("latitude",28.2278784682);
        params.put("longitude",112.9388157641 );
        List<Map<String, Object>> maps = null;
        try {
            maps = this.buildingService.queryListByActAndCity( params, 1, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(maps.size());
    }


    @Test
    public void testFeature(){
        try {
            List<HouseBuildingTags> houseBuildingTagses = this.featureTagService.queryHouseBuildingTags();
            System.out.println(houseBuildingTagses.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
