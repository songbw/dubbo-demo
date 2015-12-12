package com.flzc.scene.filing.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  案场已经反馈信息
 * Created by song on 2015/11/18.
 */
@Entity
@Table(name = "scene_feedback")
public class SceneFeedback implements Serializable {
    //反馈ID
    private Integer id;
    //案场ID
    private Integer sceneId;
    //案场名称
    private String sceneName;
    //楼盘ID
    private Integer houseId;
    //意见反馈信息
    private String feedbackInfo;
    //创建时间
    private Date createAt;
    //更新时间
    private Date updateAt;
    //版本号
    private Integer version;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "scene_id")
    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }
    @Column(name = "scene_name")
    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
    @Column(name = "house_id")
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
    @Column(name = "feedback_info")
    public String getFeedbackInfo() {
        return feedbackInfo;
    }

    public void setFeedbackInfo(String feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }
    @Column(name = "create_at")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    @Column(name = "update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
