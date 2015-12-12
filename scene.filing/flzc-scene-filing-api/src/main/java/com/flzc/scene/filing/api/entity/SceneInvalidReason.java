package com.flzc.scene.filing.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  报备无效原因信息
 * Created by song on 2015/11/17.
 */
@Entity
@Table(name = "scene_invalid_reason")
public class SceneInvalidReason implements Serializable {
    //原因ID
    private Integer id ;
    //用户ID
    private Integer userId ;
    //案场ID
    private Integer sceneId ;
    //楼盘ID
    private Integer houseId ;
    //报备ID
    private Integer filingId;
    //经纪人ID
    private Integer agencyId;
    //原因描述
    private String reasonInfo;
    //创建时间
    private Date createAt;
    //更新时间
    private Date updateAt;
    //案场名称
    private String sceneName;
    //版本号
    private Integer version ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name = "scene_id")
    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }
    @Column(name = "house_id")
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
    @Column(name = "filing_id")
    public Integer getFilingId() {
        return filingId;
    }

    public void setFilingId(Integer filingId) {
        this.filingId = filingId;
    }
    @Column(name = "agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
    @Column(name = "reason_info")
    public String getReasonInfo() {
        return reasonInfo;
    }

    public void setReasonInfo(String reasonInfo) {
        this.reasonInfo = reasonInfo;
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
    @Column(name = "scene_name")
    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
