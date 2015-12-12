package com.flzc.service.bean;

/**
 * Created by song on 2015/11/25.
 */
public class SceneInfoView {
    private Integer status = 0 ;
    private SceneInfoBean sceneInfoBean = null ;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public SceneInfoBean getSceneInfoBean() {
        return sceneInfoBean;
    }

    public void setSceneInfoBean(SceneInfoBean sceneInfoBean) {
        this.sceneInfoBean = sceneInfoBean;
    }
}
