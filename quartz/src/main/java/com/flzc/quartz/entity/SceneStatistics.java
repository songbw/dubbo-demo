package com.flzc.quartz.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 案场数据统计实体
 * Created by song on 2015/12/1.
 */
@Entity
@Table(name = "scene_statistics")
@DynamicInsert
@DynamicUpdate
public class SceneStatistics {
    private Integer id ;
    private Integer type ;
    private Integer filingStatistics ;
    private Integer visitStatistics ;
    private Integer dealStatistics ;
    private Date createAt ;
    private Date updateAt ;
    private Integer version ;
    private Integer sceneId ;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, insertable = true, updatable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "filing_statistics")
    public Integer getFilingStatistics() {
        return filingStatistics;
    }

    public void setFilingStatistics(Integer filingStatistics) {
        this.filingStatistics = filingStatistics;
    }

    @Column(name = "visit_statistics")
    public Integer getVisitStatistics() {
        return visitStatistics;
    }

    public void setVisitStatistics(Integer visitStatistics) {
        this.visitStatistics = visitStatistics;
    }

    @Column(name = "deal_statistics")
    public Integer getDealStatistics() {
        return dealStatistics;
    }

    public void setDealStatistics(Integer dealStatistics) {
        this.dealStatistics = dealStatistics;
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

    @Column(name = "scene_id")
    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }
}
