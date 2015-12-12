package com.flzc.scene.filing.service.impl;

import com.flzc.scene.filing.api.entity.SceneStatistics;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by song on 2015/12/2.
 */
public class SceneStatisticsServiceImplTest {
    SceneStatisticsServiceImpl sceneStatisticsService = new SceneStatisticsServiceImpl() ;

    @Test
    public void testQueryWeek() throws Exception {
//        List<SceneStatistics> a =  sceneStatisticsService.queryWeek(2,1447681826000l,new Date().getTime()) ;
        System.out.println(new Date().getTime());
    }

    @Test
    public void testQueryMonth() throws Exception {

    }

    @Test
    public void testQueryYear() throws Exception {

    }

    @Test
    public void testQueryWeekTotal() throws Exception {

    }

    @Test
    public void testQueryMonthTotal() throws Exception {

    }

    @Test
    public void testQueryYearTotal() throws Exception {

    }
}