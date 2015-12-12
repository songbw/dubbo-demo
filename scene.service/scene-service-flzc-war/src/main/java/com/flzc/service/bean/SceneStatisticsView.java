package com.flzc.service.bean;

import com.flzc.scene.filing.api.entity.SceneStatistics;

/**
 * Created by song on 2015/12/2.
 */
public class SceneStatisticsView {
    private Integer status = 0 ;

    private SceneStatistics sceneStatistics = new SceneStatistics() ;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SceneStatistics getSceneStatistics() {
        return sceneStatistics;
    }

    public void setSceneStatistics(SceneStatistics sceneStatistics) {
        this.sceneStatistics = sceneStatistics;
    }
}
