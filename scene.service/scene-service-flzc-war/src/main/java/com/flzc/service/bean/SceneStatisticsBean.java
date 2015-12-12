package com.flzc.service.bean;

import com.flzc.scene.filing.api.entity.SceneStatistics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2015/12/2.
 */
public class SceneStatisticsBean {

    private List<SceneStatistics> sceneStatisticses = new ArrayList<SceneStatistics>() ;
    private Integer status = 0 ;
    //（周，月，年）报备总是
    private Integer filingTotal =0 ;
    //（周，月，年）看房总是
    private Integer visitTotal = 0 ;
    //（周，月，年）成交总是
    private Integer dealTotal = 0 ;

    public List<SceneStatistics> getSceneStatisticses() {
        return sceneStatisticses;
    }

    public void setSceneStatisticses(List<SceneStatistics> sceneStatisticses) {
        this.sceneStatisticses = sceneStatisticses;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFilingTotal() {
        return filingTotal;
    }

    public void setFilingTotal(Integer filingTotal) {
        this.filingTotal = filingTotal;
    }

    public Integer getVisitTotal() {
        return visitTotal;
    }

    public void setVisitTotal(Integer visitTotal) {
        this.visitTotal = visitTotal;
    }

    public Integer getDealTotal() {
        return dealTotal;
    }

    public void setDealTotal(Integer dealTotal) {
        this.dealTotal = dealTotal;
    }
}
