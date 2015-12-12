package com.flzc.quartz.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *新房首页头条实体类
 * Created by iverson on 2015/10/20.
 */
@Entity
@Table(name = "home_page_headline")
public class HomePageHeadline implements Serializable {
    private Integer id;
    private String userName;
    private String actName;
    private String memo;
    private Integer status;
    private Date createTime;
    private Integer recapId;//活动汇总id

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
    @Column(name = "user_name", nullable = true, insertable = true, updatable = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "act_name", nullable = true, insertable = true, updatable = true)
    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    @Basic
    @Column(name = "memo", nullable = true, insertable = true, updatable = true, length = 20)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "recap_id")
    public Integer getRecapId() {
        return recapId;
    }

    public void setRecapId(Integer recapId) {
        this.recapId = recapId;
    }
}
