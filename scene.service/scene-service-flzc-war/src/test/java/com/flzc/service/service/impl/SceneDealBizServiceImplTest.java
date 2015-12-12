package com.flzc.service.service.impl;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by song on 2015/11/24.
 */
public class SceneDealBizServiceImplTest {

    @Test
    public void testAddDealImg() throws Exception {
        InputStream in = null ;
        String fileName = "D:\\a380_25%.jpg" ;
        File file = new File(fileName);
        in = new FileInputStream(file);
        SceneDealBizServiceImpl sceneDealBizService = new SceneDealBizServiceImpl();
        sceneDealBizService.addDealImg("123",in,"sbw.jpg","dealimg/",12) ;
    }
}