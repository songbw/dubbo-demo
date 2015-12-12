package com.flzc.service.service.impl;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by song on 2015/12/2.
 */
public class SceneStatisticsBizServiceImplTest {

    SceneStatisticsBizServiceImpl sceneStatisticsBizService = new SceneStatisticsBizServiceImpl() ;
    @Test
    public void testQueryWeek() throws Exception {
        sceneStatisticsBizService.queryWeek("Zmx6Yy1TRUNURVQ6MTQ0ODQ0NzA0Nzoz",1447681826000l,new Date().getTime()) ;
    }
}