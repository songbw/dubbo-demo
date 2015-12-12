package com.flzc.service.bean;

import com.flzc.scene.filing.api.entity.SceneFiling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2015/11/20.
 */
public class SceneFilingsBean {
    private List<SceneFiling> sceneFilingList = new ArrayList<SceneFiling>() ;
    private int total = 0 ;
    private Integer status = 0 ;

    public List<SceneFiling> getSceneFilingList() {
        return sceneFilingList;
    }

    public void setSceneFilingList(List<SceneFiling> sceneFilingList) {
        this.sceneFilingList = sceneFilingList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
