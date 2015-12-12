package com.flzc.service.bean;

import com.flzc.rob.api.entity.HouseBuildingTags;
import com.flzc.scene.filing.api.entity.SceneFiling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2015/11/25.
 */
public class SceneFilingView {
    private Integer status = 0 ;
    private SceneFiling sceneFiling = null ;
    private List<HouseBuildingTags> houseBuildingTagses = new ArrayList<HouseBuildingTags>() ;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SceneFiling getSceneFiling() {
        return sceneFiling;
    }

    public void setSceneFiling(SceneFiling sceneFiling) {
        this.sceneFiling = sceneFiling;
    }

    public List<HouseBuildingTags> getHouseBuildingTagses() {
        return houseBuildingTagses;
    }

    public void setHouseBuildingTagses(List<HouseBuildingTags> houseBuildingTagses) {
        this.houseBuildingTagses = houseBuildingTagses;
    }
}
