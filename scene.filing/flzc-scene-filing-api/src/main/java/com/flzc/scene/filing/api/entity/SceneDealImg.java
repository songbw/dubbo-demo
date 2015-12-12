package com.flzc.scene.filing.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  成交合同图片信息
 * Created by song on 2015/11/17.
 */
@Entity
@Table(name = "scene_deal_img")
public class SceneDealImg implements Serializable {
    //合同图片ID
    private Integer id ;
    //成交ID
    private Integer dealId;
    //图片路径
    private String imgPath;
    //创建时间
    private Date createAt;
    //更新时间
    private Date updateAt;
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
    @Column(name = "deal_id")
    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }
    @Column(name = "img_path")
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
